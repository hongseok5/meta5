package com.example.meta5.framework.security.filter;

import com.example.meta5.framework.security.dto.CustomUserDetails;
import com.example.meta5.framework.security.service.CustomUserDetailsService;
import com.example.meta5.framework.security.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // 필터링 로직을 정의
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Authorization 헤더에서 JWT 토큰을 가져옴
        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        // Authorization 헤더가 존재하고 'Bearer '로 시작하는지 확인
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // 'Bearer ' 다음의 JWT 토큰 부분만 추출
            jwt = authorizationHeader.substring(7);
            // JWT 토큰에서 이메일 추출
            email = jwtTokenUtil.extractEmail(jwt);
        }

        // 이메일이 존재하고, 현재 SecurityContext에 인증 정보가 없는 경우
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 이메일을 이용해 사용자 정보를 로드
            CustomUserDetails userDetails = (CustomUserDetails) this.customUserDetailsService.loadUserByUsername(email);

            // JWT 토큰이 유효한지 검증
            if (jwtTokenUtil.validateToken(jwt, userDetails)) {

                // 유요한 경우, 인증 객체를 생성하여 SecurityContext에 설정
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        // 다음 필터(UsernamePasswordAuthenticationFilter)로 요청을 전달하여 로그인 요청 처리
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        String[] excludedPaths = { "/user/signin", "/user/signup", "/swagger-ui", "/v3/api-docs" };
        // /public/* 경로는 필터링하지 않음
        // SecurityConfig에서 AUth 허용해도 이게 있어야함
        // 프론트에서 헤더가 있어야 함
        for (String excludedPath : excludedPaths) {
            if (path.startsWith(excludedPath)) {
                return true;
            }
        }
        return false;
    }


}