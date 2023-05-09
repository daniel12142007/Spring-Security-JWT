package com.example.springsecurityjwt.dto.response;

import lombok.Data;

@Data
public class ModelResponse {
    private String id;
    private String name;
    private String lastName;
    private String email;
}