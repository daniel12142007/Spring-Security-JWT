package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.config.JwtUtils;
import com.example.springsecurityjwt.dto.request.PersonRequest;
import com.example.springsecurityjwt.dto.response.PersonResponse;
import com.example.springsecurityjwt.repository.PersonRepo;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@Service
@ResponseBody
public class PersonService {
    private final PersonRepo personRepo;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public PersonResponse authenticate(PersonRequest personRequest) {
        if (personRepo.findByEmail(personRequest.getEmail()).isPresent()) {
            try {

                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        personRequest.getEmail(),
                        personRequest.getPassword()
                ));
                String generateToken = jwtUtils.generateToken(authentication);
                System.out.println(generateToken);
                return new PersonResponse(personRequest.getEmail(), generateToken);
            } catch (RuntimeException e) {
                throw new RuntimeException("password is not correct");
            }
        } else {
            throw new RuntimeException("email is not correct");
        }
    }
}