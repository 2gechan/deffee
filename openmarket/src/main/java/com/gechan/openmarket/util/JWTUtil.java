package com.gechan.openmarket.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

public class JWTUtil {

    private static String key = "123456789012345678901234567890";

    // jwt에 포함된 정보를 담은 map과 유효 기간을 나타내는 min
    public static String generateToken(Map<String, Object> map, int min) {
        SecretKey key = null;
        try {
            key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        String jwtString = Jwts.builder()
                .setHeader(Map.of("type", "JWT")) // 정형화된 헤더 토큰 타입 명시
                .setClaims(map) // 서명할 데이터, 사용자 정보?
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant())) // 현재 시간 기준 토큰 생성 시간
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant())) // 토큰 만료 시간
                .signWith(key) // 위에서 생성한 key로 서명
                .compact();

        return jwtString;
    }

    public static Map<String, Object> validateToken(String token) {
        Map<String, Object> claim = null;

        try {
            SecretKey key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
            claim = Jwts.parserBuilder()// jwt parser를 생성 하기 위한 builder
                    .setSigningKey(key) // 서명 검증에 사용할 키를 설정, 서명할 때와 동일한 비밀키를 사용하여 검증해야 한다.
                    .build()
                    .parseClaimsJws(token) // jwt 문자열을 파싱하고, 서명을 검증하여 반환한다.
                    .getBody(); // jwt payload(claim)를 추출하여 claim 객체로 반환
        }catch(MalformedJwtException malformedJwtException){
            throw new RuntimeException("MalFormed");
        }catch(ExpiredJwtException expiredJwtException){
            throw new RuntimeException("Expired");
        }catch(InvalidClaimException invalidClaimException){
            throw new RuntimeException("Invalid");
        }catch(JwtException jwtException){
            throw new RuntimeException("JWTError");
        }catch(Exception e){
            throw new RuntimeException("Error");
        }

        return claim;
    }
}
