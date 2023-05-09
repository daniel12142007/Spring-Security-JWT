package com.example.springsecurityjwt.api;

import com.example.springsecurityjwt.dto.request.ModelRequest;
import com.example.springsecurityjwt.dto.response.ModelResponse;
import com.example.springsecurityjwt.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/api")
public class Api {
    private final ModelService modelService;

    @PostMapping("/save")
    @PermitAll
    @Operation(summary = "save user", description = "for all")
    public ModelResponse save(@RequestBody ModelRequest modelRequest) {
        return modelService.save(modelRequest);
    }

    @GetMapping("/get/name")
    @PermitAll
    @Operation(summary = "get by name")
    public List<ModelResponse> getByName(@RequestParam String name) {
        return modelService.getByName(name);
    }
}