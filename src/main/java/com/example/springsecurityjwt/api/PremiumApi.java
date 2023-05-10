package com.example.springsecurityjwt.api;

import com.example.springsecurityjwt.dto.request.LaptopRequest;
import com.example.springsecurityjwt.dto.response.LaptopResponse;
import com.example.springsecurityjwt.service.PremiumService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/premium")
public class PremiumApi {
    private final PremiumService premiumService;

    @PostMapping("save/laptop")
    @PreAuthorize("hasAnyAuthority('ADMIN','PREMIUM')")
    @Operation(summary = "save laptop for admin or premium")
    public LaptopResponse save(@RequestBody LaptopRequest laptopRequest, @RequestParam Long id) {
        return premiumService.saveLaptop(id, laptopRequest);
    }
}
