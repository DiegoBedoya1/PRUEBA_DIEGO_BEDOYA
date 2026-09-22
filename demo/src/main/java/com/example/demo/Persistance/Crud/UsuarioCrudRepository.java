package com.example.demo.Persistance.Crud;

import com.example.demo.Persistance.Entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
}
