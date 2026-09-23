package com.example.demo.Domain.Service;

import com.example.demo.Domain.DTO.Product;
import com.example.demo.Domain.Repository.ProductRepository;
import com.example.demo.enums.TipoAlmacenamiento;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repo;

    public List<Product> showAll(){
        return repo.showAll();
    }

    public Product showById(Long id){
        return repo.showById(id);
    }

    public Product create(Product product){
        return repo.create(product);
    }

    public List<Product> showByStorageType(TipoAlmacenamiento tipoAlmacenamiento){
        return repo.showByStorageType(tipoAlmacenamiento);
    }

    public Product update(Long id, Product product){
        return repo.update(id,product);
    }

    public Product delete(Long id){
        return repo.delete(id);
    }

    public List<Product> showAllExpired(LocalDate expirationDate){
        return repo.showAllExpired(expirationDate);
    }

    public List<Product> showExpiringSoon(Long days){
        return repo.showExpiringSoon(days);
    }
}
