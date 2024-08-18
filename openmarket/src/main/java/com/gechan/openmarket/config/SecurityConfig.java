package com.gechan.openmarket.config;

import com.gechan.openmarket.security.handler.LoginAccessDeniedHandler;
import com.gechan.openmarket.security.handler.LoginFailHandler;
import com.gechan.openmarket.security.handler.LoginSuccessHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@Log4j2
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.debug("=".repeat(50));
        log.debug("security FilterChain");
        log.debug("=".repeat(50));

        // spring security cors 설정
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });

        // 세션을 만들지 않는 설정
        http.sessionManagement(config -> {
            config.sessionCreationPolicy(SessionCreationPolicy.NEVER);
        });

        // csrf disabled
        http.csrf(config -> config.disable());

        // 로그인 시 success 핸들러, failed 핸들러
        http.formLogin(config -> {
            config.loginPage("/api/member/login");
            config.successHandler(new LoginSuccessHandler());
            config.failureHandler(new LoginFailHandler());
        });

        http.exceptionHandling(config -> {
            config.accessDeniedHandler(new LoginAccessDeniedHandler());
        });


        return http.build();
    }

    // cors 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        // cors 설정을 위한 옵션을 제공하는 객체
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 모든 도메인에 대해 허용
        // ex) localhost:3000 을 설정하여 해당 도메인에서만 접근 가능하도록 설정
        // "*"로 설정하게 되면 어느 도메인에서든 서버에 접근이 가능하게 된다.
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));

        // 허용 메서드
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        // 허용된 요청 헤더 목록
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        // 자격 증명(쿠키, 인증 헤더 등)을 포함한 요청 허용
        corsConfiguration.setAllowCredentials(true);

        // url 패턴별 cors 설정 적용 객체
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // 모든 경로에 앞서 정의한 cors 설정 등록
        // "/member/**" 같이도 설정 가능
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }


}
