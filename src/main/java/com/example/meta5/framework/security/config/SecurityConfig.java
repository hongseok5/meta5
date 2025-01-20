package com.example.meta5.framework.security.config;

import com.example.meta5.config.WebMvcConfig;
import com.example.meta5.framework.security.filter.CustomUsernamePasswordAuthenticationFilter;
import com.example.meta5.framework.security.filter.JwtRequestFilter;
import com.example.meta5.framework.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Spring Security를 활성화
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    CustomUserDetailsService userDetailsService;

    // HTTP 보안 설정을 구성하는 메서드
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(CorsConfig.corsConfigurationSource()))
                .authorizeHttpRequests(authorizeRequests -> // HTTP 요청에 대한 보안 규칙을 정의
                        authorizeRequests
                                    .requestMatchers("/user/signup", "/user/signin", "/swagger-ui/**" ,"/v3/**",  "/swagger-resources/**", "/webjars/**", "/api/**").permitAll() // 회원가입, 로그인에 대한 접근 허용
                                .anyRequest().authenticated() // 그 외의 모든 요청은 인증을 요구
                )
//                .httpBasic(withDefaults()) // HTTP Basic 인증 활성화 -> 주로 간단한 테스트를 위해 사용됨. JWT토큰 인증을 사용하면 없어도 됨
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // JWT 필터 추가, UsernamePassword 필터 앞에서 수행됨.
                .csrf(csrf -> csrf.disable());
        return http.build(); // HTTP 보안 설정을 빌드하여 반환
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService) // 사용자 정보를 로드
                .passwordEncoder(passwordEncoder()); // 비밀번호 검증
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
