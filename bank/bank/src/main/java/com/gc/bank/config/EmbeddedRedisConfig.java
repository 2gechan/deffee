package com.gc.bank.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import java.io.IOException;

@Configuration
@Profile("dev")
public class EmbeddedRedisConfig {

    private RedisServer redisServer;

    @PostConstruct
    public void start() throws IOException {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @PreDestroy
    public void stop() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
