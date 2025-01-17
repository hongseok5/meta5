package com.example.meta5.controller;


import com.example.meta5.dto.LoginRequest;
import com.example.meta5.dto.LoginResponse;
import com.example.meta5.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;


@RestController
public class AuthController {


    private AuthenticationManager authenticationManager;

    private JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // JWT 토큰 생성
        //String jwtToken = jwtTokenService.generateToken(authentication);

        //return new LoginResponse(jwtToken);
        return new LoginResponse();
    }
}