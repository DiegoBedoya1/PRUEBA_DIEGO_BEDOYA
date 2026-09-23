package com.example.demo.web.Controller;

import com.example.demo.Domain.DTO.AuthResponse;
import com.example.demo.Domain.DTO.LoginRequest;
import com.example.demo.Domain.DTO.User;
import com.example.demo.Domain.Service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(service.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getProfile(Authentication authentication){
        return ResponseEntity.ok(service.getProfile(authentication.getName()));
    }
}
