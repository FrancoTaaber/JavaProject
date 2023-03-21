package com.example.photosapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class LogConfig {
    @Value("${logs.path}")
    private String logFile;
    public Path getLogsFilePath() {
        return Paths.get(logFile);
    }
}
