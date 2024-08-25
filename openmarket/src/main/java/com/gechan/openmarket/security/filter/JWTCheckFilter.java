package com.gechan.openmarket.security.filter;

import com.gechan.openmarket.dto.MemberDTO;
import com.gechan.openmarket.util.JWTUtil;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    // true 이면 필터 체크 하지 않는다.
    // false 이면 필터 체크
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String path = request.getRequestURI();

        log.debug("요청 URI ........." + path);

        // 로그인 시도는 인증 토큰 발급 전이기 때문에 true로 리턴하여 JWTCheckFilter를 수행하지 않는다.
        if (path.startsWith("/api/member/")) {
            return true;
        }

        if (path.startsWith("api/products/view/")) {
            return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String requestAuthHeader = request.getHeader("Authorization");

        try {
            // Bearer accessToken ...
            String accessToken = requestAuthHeader.substring(7);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);

            String id = (String) claims.get("id");
            String pw = (String) claims.get("pw");
            String nickname = (String) claims.get("nickname");
            List<String> roleNames = (List<String>) claims.get("roleNames");

            MemberDTO memberDTO = new MemberDTO(id, pw, nickname, roleNames);

            // UsernamePasswordAuthenticationToken 객체를 생성하여
            // 인증 정보를 포함한 토큰을 만든다. 이 토큰은 Spring Security에서 사용된다.
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(memberDTO, pw, memberDTO.getAuthorities());

            // Spring Security의 컨텍스트에 현재 인증된 사용자 정보를 설정한다.
            // 이후 요청에서 인증된 사용자로서의 권한을 갖게 된다.
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            // 다음 filter 수행
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("ERROR", "ERROR_ACCESS_TOKEN"));

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }

    }
}
