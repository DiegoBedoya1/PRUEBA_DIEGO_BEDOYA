package com.example.demo.Domain.Service;

import com.example.demo.Persistance.Crud.UsuarioCrudRepository;
import com.example.demo.Persistance.Entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUsersDetialsService implements UserDetailsService {
    private final UsuarioCrudRepository usuarioCrud;

    @Override
    public UserDetails loadUserByUsername(@NonNull String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioCrud.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return User.withUsername(usuario.getCorreo())
                .password(usuario.getContraseña())
                .authorities("ROLE_"+usuario.getRol())
                .build();
    }

}
