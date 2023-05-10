package com.example.springsecurityjwt.dto.request;

import com.example.springsecurityjwt.model.enusm.State;
import lombok.Data;

@Data
public class LaptopRequest {
    private String model_name;
    private State state;
}