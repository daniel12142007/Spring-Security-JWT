package com.example.springsecurityjwt.config;

import com.example.springsecurityjwt.model.Person;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class JwtUtils {
    private final JwtConfig jwt;

    public String generateToken(Authentication authentication) {
        Person person = (Person) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(person.getEmail())
                .signWith(SignatureAlgorithm.HS512, jwt.getSecretKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() * (jwt.getAfterDays() * 65000)))
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwt.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean verifyToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(jwt.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}