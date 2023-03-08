package com.example.photosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Swagger docs - http://localhost:8080/swagger-ui.html
public class PhotosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotosApiApplication.class, args);
    }

}
