package com.gc.bank.domains.auth.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RefreshTokenService {

    private final RedisTemplate<String, String> redisTemplate;

    public RefreshTokenService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void save(Long memberId, String refreshToken, long refreshTtl) {
        redisTemplate.opsForValue()
                .set(
                        "RT:"+memberId,
                        refreshToken,
                        refreshTtl,
                        TimeUnit.MILLISECONDS
                );
    }

    public String get(Long memberId) {
        return redisTemplate.opsForValue().get("RT:" + memberId);
    }

    public void delete(Long memberId) {
        redisTemplate.delete("RT:" + memberId);
    }
}
