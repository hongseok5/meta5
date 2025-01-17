package com.example.meta5.framework.security.util;

import com.example.meta5.framework.security.dto.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    // secret.key 파일의 고정된 비밀 키 사용
    @Value("${secret.key.path}")
    private Resource secretKeyResource;

    private String secretKey;
    private Key key;



    @PostConstruct // secret key 초기화 안전성을 위해 모든 의존성이 주입된 후 호출되도록함
    public void init() {
        try {
            String secretKey = new String(Files.readAllBytes(Paths.get(secretKeyResource.getURI())));
            this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("secret.key 를 읽어올 수 없습니다", e);
        }
    }

    // JWT 토큰에서 이메일을 추출
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject); // subject를 이메일로 사용
    }

    // JWT 토큰에서 만료 시간을 추출
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // JWT 토큰에서 특정 클레임을 추출
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // JWT 토큰에서 모든 클레임을 추출
    private Claims extractAllClaims(String token) {
        /**
         * java.lang.IllegalArgumentException: signing key cannot be null or empty.
         * 	at io.jsonwebtoken.lang.Assert.notEmpty(Assert.java:201) ~[jjwt-api-0.11.5.jar:0.11.5]
         * 	at io.jsonwebtoken.impl.DefaultJwtParserBuilder.setSigningKey(DefaultJwtParserBuilder.java:151) ~[jjwt-impl-0.11.5.jar:0.11.5]
         * 	at com.example.meta5.framework.security.util.JwtTokenUtil.extractAllClaims(JwtTokenUtil.java:55) ~[main/:na]
         */
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // JWT 토큰이 만료되었는지 확인
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 사용자 정보를 기반으로 JWT 토큰 생성
    public String generateToken(CustomUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /*
        public String generateToken(User user) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("email", user.getEmail());
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getEmail())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24시간 유효
                    .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                    .compact();
    */
    // 클레임과 주제를 기반으로 JWT 토큰 생성
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24시간 동안 유효
                .signWith(key, SignatureAlgorithm.HS256).compact();
        //                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256)).compact();

    }

    // JWT 토큰의 유효성 검증
    public Boolean validateToken(String token, CustomUserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
