package com.example.meta5.dto;

public class LoginResponse {
    private String token;
    private String message;

    // 기본 생성자
    public LoginResponse() {}

    // 생성자
    public LoginResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    // Getter and Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
