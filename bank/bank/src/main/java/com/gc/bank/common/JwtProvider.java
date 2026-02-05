package com.gc.bank.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final String SECRET_KEY = "bank-secret-key-bank-secret-key-bank-secret-key";
    private final long ACCESS_TOKEN_EXP = 1000L * 60 * 30; // 30분
    @Getter
    private final long REFRESH_TOKEN_EXP = 1000L * 60 * 60 * 24; // 1일
    private final Key key =
            Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String createAccessToken(Long memberId) {
        return Jwts.builder()
                .setSubject(memberId.toString()) // 토큰의 주인
                .claim("type", "access")
                .setIssuedAt(new Date()) // 토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXP)) // 토큰 만료 시간
                // 위조 방지 서명
                .signWith(
                        key,
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    public String createRefreshToken(Long memberId) {
        return Jwts.builder()
                .setSubject(memberId.toString()) // 토큰의 주인
                .claim("type", "refresh")
                .setIssuedAt(new Date()) // 토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXP)) // 토큰 만료 시간
                // 위조 방지 서명
                .signWith(
                        key,
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    public Long validateAccessTokenAndGetUserId(String token) {
        Claims claims = parseClaims(token);

        // 🔒 access 토큰인지 확인
        if (!"access".equals(claims.get("type"))) {
            throw new JwtException("Access Token이 아닙니다");
        }

        return Long.valueOf(claims.getSubject());
    }

    public Long validateRefreshTokenAndGetUserId(String token) {
        Claims claims = parseClaims(token);

        if (!"refresh".equals(claims.get("type"))) {
            throw new JwtException("Refresh Token이 아닙니다");
        }

        return Long.valueOf(claims.getSubject());
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token) // 토큰 만료 체크도 포함
                .getBody();
    }

}
