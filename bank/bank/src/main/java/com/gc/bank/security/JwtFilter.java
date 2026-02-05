package com.gc.bank.security;

import com.gc.bank.common.JwtProvider;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            Long memberId = jwtProvider.validateAccessTokenAndGetUserId(token);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            memberId,
                            null,
                            List.of(
                                    new SimpleGrantedAuthority("ROLE_USER")
                            )
                    );

            ((UsernamePasswordAuthenticationToken) authentication)
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

        } catch (ExpiredJwtException e) {
            // 토큰 문제 → 인증 정보 제거
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;

        } catch (JwtException | IllegalArgumentException e) {
            // IllegalArgumentException null, 빈 토큰 등 파싱 불가
            // JwtException 서명 위조, 형식 오류, 잘못된 토큰
            SecurityContextHolder.clearContext();
        }


        filterChain.doFilter(request, response);
    }
}
