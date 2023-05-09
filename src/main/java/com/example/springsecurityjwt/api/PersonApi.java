package com.example.springsecurityjwt.api;

import com.example.springsecurityjwt.dto.request.PersonRequest;
import com.example.springsecurityjwt.dto.response.PersonResponse;
import com.example.springsecurityjwt.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/person")
public class PersonApi {
    private final PersonService personService;

    @PostMapping("/login")
    @PermitAll
    @Operation(summary = "login account")
    public PersonResponse login(@RequestBody PersonRequest personRequest) {
        return personService.authenticate(personRequest);
    }
}