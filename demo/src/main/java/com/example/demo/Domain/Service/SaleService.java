package com.example.demo.Domain.Service;

import com.example.demo.Domain.DTO.Sale;
import com.example.demo.Domain.Repository.SaleRepository;
import com.example.demo.Persistance.Crud.UsuarioCrudRepository;import com.example.demo.Persistance.Entity.Usuario;
import com.example.demo.enums.Rol;import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository repo;
    private final UsuarioCrudRepository crudUser;

    public Sale register(Sale sale){
        return repo.register(sale);
    }

    public List<Sale> showAllByUser(Long id, Authentication authentication){
        Usuario usuario = crudUser.findByCorreo(authentication.getName())
                .orElseThrow(() -> new RuntimeException("usuario no encontrado"));
        if(usuario.getRol().equals(Rol.ADMIN)){
            return repo.showAllByUser(id);
        }
        if(!usuario.getId().equals(id)){
            throw new RuntimeException("No tienes permiso para consultar pedidos de este usuario");
        }
        return repo.showAllByUser(id);
    }

    public Sale showById(Long id){
        return repo.showById(id);
    }
}
