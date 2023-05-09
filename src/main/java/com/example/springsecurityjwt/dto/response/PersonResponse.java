package com.example.springsecurityjwt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonResponse {
    private String email;
    //  This parameter returns a token
    private String jwt;
}