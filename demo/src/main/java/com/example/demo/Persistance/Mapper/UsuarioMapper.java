package com.example.demo.Persistance.Mapper;

import com.example.demo.Domain.DTO.User;
import com.example.demo.Persistance.Entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "correo", target = "mail"),
            @Mapping(source = "contraseña", target = "password"),
            @Mapping(source = "rol", target = "role")
    })
    User toUSer(Usuario usuario);

    @InheritInverseConfiguration
    Usuario toUsuario(User user);
}
