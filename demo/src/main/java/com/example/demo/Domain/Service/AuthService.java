package com.example.demo.Domain.Service;

import com.example.demo.Domain.DTO.AuthResponse;
import com.example.demo.Domain.DTO.LoginRequest;
import com.example.demo.Domain.DTO.User;
import com.example.demo.Persistance.Crud.UsuarioCrudRepository;import com.example.demo.Persistance.Entity.Usuario;
import com.example.demo.Persistance.Mapper.UsuarioMapper;import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final JWTService jwt;
    private final AuthenticationManager manager;
    private final UsuarioCrudRepository usuarioCrud;
    private final UsuarioMapper usuarioMapper;

    public AuthResponse login(LoginRequest request){
        var authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword())
        );
        String token = jwt.generateToken((UserDetails)authentication.getPrincipal());
        return new AuthResponse(token);
    }

    public User getProfile(String correo){
        Usuario usuario = usuarioCrud.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuarioMapper.toUSer(usuario);
    }
}
