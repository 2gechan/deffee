package com.gc.bank.config;

import com.gc.bank.common.JwtProvider;
import com.gc.bank.domains.bank.repository.BankMemberRepository;
import com.gc.bank.domains.auth.service.RefreshTokenService;
import com.gc.bank.security.JwtFilter;
import com.gc.bank.types.entity.Member;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final BankMemberRepository bankMemberRepository;

    public SecurityConfig(JwtFilter jwtFilter, JwtProvider jwtProvider, RefreshTokenService refreshTokenService, BankMemberRepository bankMemberRepository) {
        this.jwtFilter = jwtFilter;
        this.jwtProvider = jwtProvider;
        this.refreshTokenService = refreshTokenService;
        this.bankMemberRepository = bankMemberRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                // 설정한 url 이외의 요청은 인증 필요
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/h2-console/**",
                                "/oauth2/**",
                                "/api/v1/auth/**",
                                "/login/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // 개발환경 전용 설정으로 iframe 페이지 로드 허용(h2 console 사용을 위해)
                .headers(headers ->
                        headers.frameOptions(frame -> frame.disable())
                )
                // OAuth2 로그인 기능 활성화
                .oauth2Login(oauth2 -> oauth2
                        .successHandler((request, response, authentication) -> {
                            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

                            String githubId = oAuth2User.getAttribute("id").toString();

                            Member findMember = bankMemberRepository.findByUsername(githubId)
                                    .orElseGet(() -> bankMemberRepository.save(
                                            new Member(githubId)
                                    ));

                            Long memberId = findMember.getId();

                            String accessToken = jwtProvider.createAccessToken(memberId);
                            String refreshToken = jwtProvider.createRefreshToken(memberId);
                            long refreshTtl = jwtProvider.getREFRESH_TOKEN_EXP();
                            refreshTokenService.save(memberId, refreshToken, refreshTtl);

                            response.addHeader(
                                    "Set-Cookie",
                                    "refreshToken=" + refreshToken +
                                            "; HttpOnly; Path=/api/v1/auth/refresh"
                            );

                            response.sendRedirect(
                                    "http://localhost:9090/api/v1/auth/success?accessToken=" + accessToken
                            );
                        }))
                .formLogin(form -> form.disable())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Unauthorized\"}");
                        })
                );

        return http.build();

    }
}

