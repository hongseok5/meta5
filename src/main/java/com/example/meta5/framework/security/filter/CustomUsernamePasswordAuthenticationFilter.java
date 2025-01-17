package com.example.meta5.framework.security.filter;

import com.example.meta5.framework.security.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtTokenUtil jwtTokenUtil;

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        super.setAuthenticationManager(authenticationManager);
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // 사용자가 성공적으로 인증되었을 때 JWT 생성
        String username = authResult.getName();
        String jwt = jwtTokenUtil.generateToken(username);

        // JWT를 응답 헤더에 추가
        response.setHeader("Authorization", "Bearer " + jwt);

        // 부모 클래스의 성공 처리 호출
        super.successfulAuthentication(request, response, chain, authResult);
    }

}
