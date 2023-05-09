package com.example.springsecurityjwt.api;

import com.example.springsecurityjwt.dto.response.ModelResponse;
import com.example.springsecurityjwt.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/admin")
public class AdminApi {
    private final ModelService modelService;

    @PutMapping("/raise")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "raise premium", description = "raise user for admin")
    public ModelResponse raisePremium(@RequestParam Long id) {
        return modelService.raisePremium(id);
    }

    @PutMapping("/demotion")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "demotion user", description = "demotion PREMIUM for admin")
    public ModelResponse demotion(@RequestParam Long id) {
        return modelService.demotion(id);
    }

    @PutMapping("raise/Admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "raise admin", description = "raise ADMIN for admin with key")
    public ModelResponse raiseAdmin(@RequestParam Long id, @RequestParam String key) {
        return modelService.raiseAdmin(id, key);
    }
<<<<<<< HEAD
}
=======

    @DeleteMapping("delete/by/id")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "delete user and premuim")
    public ModelResponse deleteById(@RequestParam Long id) {
        return modelService.ban(id);
    }
}
>>>>>>> 248887f (Initial commit)
