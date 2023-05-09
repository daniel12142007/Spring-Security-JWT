package com.example.springsecurityjwt.dto.request;

import lombok.Data;

@Data
public class PersonRequest {
    private String email;
    private String password;
}