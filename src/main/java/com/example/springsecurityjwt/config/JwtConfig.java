package com.example.springsecurityjwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "app.jwt")
public class JwtConfig {
    private Long AfterDays;
    private String secretKey;
    private String tokenPrefix;

    public void setTokenAfterDays(Long token) {
        this.AfterDays = token * 650000;
    }

    public String getAuthorized() {
        return HttpHeaders.AUTHORIZATION;
    }
}