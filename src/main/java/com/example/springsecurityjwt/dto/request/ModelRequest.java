package com.example.springsecurityjwt.dto.request;

import lombok.Data;

@Data
public class ModelRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
}