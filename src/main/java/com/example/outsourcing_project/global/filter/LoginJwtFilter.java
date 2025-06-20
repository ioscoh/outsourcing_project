package com.example.outsourcing_project.global.filter;

import com.example.outsourcing_project.global.util.LoginJwtUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor        // 생성자 주입 Lombok
public class LoginJwtFilter implements Filter {

    private final LoginJwtUtil loginJwtUtil;

    /** 토큰 검증이 필요 없는 경로들 */
    private static final Set<String> WHITELIST = Set.of(
            "/members/signup",
            "/members/login",
            "/api/members/signup",
            "/api/members/login"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  req  = (HttpServletRequest) request;
        HttpServletResponse res  = (HttpServletResponse) response;

        String uri  = req.getRequestURI();
        String auth = req.getHeader("Authorization");

        // 처음 로그인 하는 거야? 그럼 JWT 토큰이 없을 것이니 토큰 먼저 발급 받아!
        if (WHITELIST.contains(uri)) {
            chain.doFilter(request, response);
            return;
        }

        // 로그인 하는게 아니네? 그럼 JWT 토큰 있어?
        if (auth == null || !auth.startsWith("Bearer ")) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT 토큰이 필요 합니다.");
            return;
        }

       // JWT 토큰이 있구만 그럼 JWT 토큰 유효해?
       // 1. Secret Key 내가 만든 거랑 동일해?
       // 2. JWT 시간 만료 된거 아니야?
        String jwt = auth.substring(7);

        // Secret Key 는 내가 만든게 맞는지 검증 만료 기간 지났는지 검증
        if (!loginJwtUtil.validateToken(jwt)) {// 서명‧만료 검증
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "JWT 토큰이 유효하지 않습니다.");
            return;
        }

        // 그럼 너가 가져오 JWT 토큰은 유효한 토큰이군! 통과!


        // 이제부터는 JWT 토큰에 어떤 정보가 들어가 있는지 살펴보자!

        // 만약 요청한 API 가 관리자 전용 API 인 경우에
        if (uri.startsWith("/api/admin") && !loginJwtUtil.hasRole(jwt, "ADMIN")) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다.");
            return;
        }// 만약 요청한 API 가 사용자 전용 API 인 경우에
        if (uri.startsWith("/api/user") && !loginJwtUtil.hasRole(jwt, "USER")) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다.");
            return;
        }

        // 전용 API가 아닌 일반 API의 경우
        chain.doFilter(request, response);
    }
}
