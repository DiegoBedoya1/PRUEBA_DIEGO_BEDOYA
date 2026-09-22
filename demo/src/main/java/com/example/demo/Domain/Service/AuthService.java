package com.example.demo.Domain.Service;

import com.example.demo.Domain.DTO.AuthResponse;
import com.example.demo.Domain.DTO.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final JWTService jwt;
    private final AuthenticationManager manager;

    public AuthResponse login(LoginRequest request){
        var authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword())
        );
        String token = jwt.generateToken((UserDetails)authentication.getPrincipal());
        return new AuthResponse(token);
    }
}
