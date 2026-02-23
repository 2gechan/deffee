package com.gc.bank.redis.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisAccountLockManager implements AccountLockManager {

    private final StringRedisTemplate redisTemplate;

    public RedisAccountLockManager(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void lock(Long accountId) {
        String key = "lock:account:" + accountId;

        while (Boolean.FALSE.equals(
                redisTemplate.opsForValue().setIfAbsent(key, "locked", Duration.ofSeconds(5))
        )) {
            try {
                Thread.sleep(50);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void unlock(Long accountId) {
        redisTemplate.delete("lock:account:" + accountId);
    }
}
