package com.example.springsecurityjwt.model.enusm;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    PREMIUM,
    USER;
    @Override
    public String getAuthority() {
        return name();
    }
}
