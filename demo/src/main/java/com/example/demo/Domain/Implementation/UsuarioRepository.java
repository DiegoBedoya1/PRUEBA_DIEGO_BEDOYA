package com.example.demo.Domain.Implementation;

import com.example.demo.Domain.DTO.User;
import com.example.demo.Domain.Repository.UserRepository;
import com.example.demo.Persistance.Crud.UsuarioCrudRepository;
import com.example.demo.Persistance.Entity.Usuario;
import com.example.demo.Persistance.Mapper.UsuarioMapper;
import com.example.demo.enums.Rol;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UsuarioRepository implements UserRepository {
    private final UsuarioCrudRepository crud;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        Rol rol = user.getRole() == null ? Rol.USER : user.getRole();
        User user1 = new User(null,user.getName(), user.getMail(), encodedPassword, rol);
        Usuario usuario = mapper.toUsuario(user1);
        return mapper.toUSer(crud.save(usuario));
    }
}
