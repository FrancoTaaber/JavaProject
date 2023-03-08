package com.example.photosapi.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiter {
    private final Bucket bucket = Bucket.builder()
            .addLimit(Bandwidth.simple(10, Duration.ofMinutes(1)))
            .build();
    public boolean tryConsume() {
        return bucket.tryConsume(1);
    }
}
