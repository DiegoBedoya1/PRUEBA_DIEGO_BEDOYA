package com.example.demo.web.Controller;

import com.example.demo.Domain.DTO.User;
import com.example.demo.Domain.Service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/new")
    public ResponseEntity<User> create(@Valid @RequestBody User user){
        return ResponseEntity.ok(service.create(user));
    }
}
