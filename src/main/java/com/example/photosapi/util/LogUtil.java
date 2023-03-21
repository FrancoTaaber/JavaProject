package com.example.photosapi.util;

import com.example.photosapi.model.Photo;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LogUtil {

    public void handleLog(Logger log, HttpServletRequest request) {
        String id = request.getSession().getId();
        String method = request.getMethod();

        log.info("Session={} Method={}", id, method);
    }

    public void handleLog(Logger log, HttpServletRequest request, Photo photo) {
        String id = request.getSession().getId();
        String method = request.getMethod();
        String itemId = String.valueOf(photo.getId());

        log.info("Session={} Method={} ItemId={} Body={}", id, method, itemId, photo.toString());
    }

    public void handleLog(Logger log, HttpServletRequest request, Photo oldPhoto, Photo newPhoto) {
        String id = request.getSession().getId();
        String method = request.getMethod();
        String itemId = String.valueOf(oldPhoto.getId());
        String[] difference = oldPhoto.getDifferences(newPhoto);

        log.info("Session={} Method={} ItemId={} Body={} OldBody={}", id, method, itemId, difference[0], difference[1]);
    }

    public void handleLog(Logger log, HttpServletRequest request, int itemId, Photo photo) {
        String id = request.getSession().getId();
        String method = request.getMethod();

        log.info("Session={} Method={} ItemId={} Body={}", id, method, itemId, photo.toString());
    }

    public List<String> readLogFile(Path path, Logger log) throws FileNotFoundException {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            log.error("Error reading log file", e);
            throw new FileNotFoundException();
        }
    }

}
