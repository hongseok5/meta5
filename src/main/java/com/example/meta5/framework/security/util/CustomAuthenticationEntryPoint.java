package com.example.meta5.framework.security.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        responseData.put("error", "Unauthorized");
        responseData.put("message", "Session expired or invalid");

        response.getWriter().write(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(responseData));
    }


}
