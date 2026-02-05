package com.gc.bank.domains.auth.controller;

import com.gc.bank.common.JwtProvider;
import com.gc.bank.domains.auth.service.RefreshTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    public AuthController(JwtProvider jwtProvider, RefreshTokenService refreshTokenService) {
        this.jwtProvider = jwtProvider;
        this.refreshTokenService = refreshTokenService;
    }

//    @GetMapping("/callback")
//    public void callback(
//            @AuthenticationPrincipal OAuth2User oAuth2User,
//            HttpServletResponse response
//            ) throws IOException {
//
//        String githubId = oAuth2User.getAttribute("id").toString();
//
//        String accessToken = jwtProvider.createAccessToken(githubId);
//        String refreshToken = jwtProvider.createRefreshToken(githubId);
//
//        response.addHeader(
//                "Set-Cookie",
//                "refreshToken="+refreshToken +
//                        "; Secure; SameSite=Strict; HttpOnly; Path=/api/v1/auth/refresh"
//        );
//
//        // redirect URL
//        response.sendRedirect("http://localhost:9090/api/v1/auth/success?accessToken=" + accessToken);
//
//    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(
            @CookieValue("refreshToken") String refreshToken
    ) {

        String token = refreshToken;

        Long memberId = jwtProvider.validateRefreshTokenAndGetUserId(token);

        // Redis에 저장된 refreshToken 조회
        String savedToken =
                refreshTokenService.get(memberId);

        if (savedToken == null || !savedToken.equals(token)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        String newAccessToken =
                jwtProvider.createAccessToken(memberId);

        return ResponseEntity.ok(
                Map.of("accessToken", newAccessToken)
        );

    }

    @GetMapping("/success")
    public String success(@RequestParam String accessToken) {
        return "accessToken = " + accessToken;
    }

}
