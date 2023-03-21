package com.example.photosapi.controller;

import com.example.photosapi.config.LogConfig;
import com.example.photosapi.model.Log;
import com.example.photosapi.util.LogUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("api/v1/logs")
@OpenAPIDefinition(info =
@Info(
        title = "Logs API",
        version = "1.0",
        description = "API for retrieving logs"
)
)

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LogController {
    @Autowired
    private LogConfig logConfig;
    private static final Logger log = LogManager.getLogger(LogController.class);
    private static final String LOG_REGEX = "^(\\d{2}:\\d{2}:\\d{2}.\\d{3}) \\[.+ INFO.+ - Session=(.+) Method=(.+) ItemId=(.+) Body=(.+)( OldBody=(.+))?$";
    private static final String LOG_DELETE_REGEX = "^(\\d{2}:\\d{2}:\\d{2}.\\d{3}) \\[.+ INFO.+ - Session=(.+) Method=(.+) ItemId=(.+) Body=(.+)$";
    private static final Pattern LOG_PATTERN = Pattern.compile(LOG_REGEX);
    private static final Pattern LOG_DELETE_PATTERN = Pattern.compile(LOG_DELETE_REGEX);
    private final LogUtil logUtil = new LogUtil();

    @GetMapping()
    @Operation(summary = "Retrieve all logs",
            description = "Retrieves a list of all logs in the system",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval of logs"),
                    @ApiResponse(responseCode = "404", description = "Logs not found")
            })
    public List<Log> getLogs() throws FileNotFoundException {
        List<Log> logs = new ArrayList<>();
        List<String> logLines = logUtil.readLogFile(logConfig.getLogsFilePath(), log);

        for (String logLine : logLines) {
            Matcher matcher = LOG_PATTERN.matcher(logLine);
            if (matcher.matches()) {
                String time = matcher.group(1);
                String sessionId = matcher.group(2);
                String method = matcher.group(3);
                String itemId = matcher.group(4);
                String body = matcher.group(5).replace("'", "");
                if (method.equals("PUT") || method.equals("POST") || method.equals("DELETE")) {
                    Log log = new Log();
                    log.setTime(time);
                    log.setSessionId(sessionId);
                    log.setMethod(method);
                    log.setItemId(itemId);
                    String[] bodyAndOldBody = body.split(" OldBody=");
                    String bodyValue = bodyAndOldBody[0];
                    String oldBodyValue = null;
                    if (bodyAndOldBody.length > 1) {
                        oldBodyValue = bodyAndOldBody[1];
                    }
                    // remove the "{" and "}" from the body string
                    bodyValue = bodyValue.substring(1, bodyValue.length() - 1);
                    log.setBody(bodyValue);
                    if (oldBodyValue != null) {
                        // remove the "{" and "}" from the oldBody string
                        oldBodyValue = oldBodyValue.substring(1, oldBodyValue.length() - 1);
                        log.setOldBody(oldBodyValue);
                    }
                    logs.add(log);
                }

            }
        }

        return logs;
    }
}
