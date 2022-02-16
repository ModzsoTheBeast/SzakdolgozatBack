package com.example.szakdolgozatBack.Authentication.Jwt;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    private final String secretKey = "securesecuresecuresecuresecuresecure";
    private final String tokenPrefix = "Bearer ";
    private final Integer tokenExpirationAfterDays = 10;

    public JwtConfig() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public Integer getTokenExpirationAfterDays() {
        return tokenExpirationAfterDays;
    }
}
