package com.example.demo.Domain.Repository;

import com.example.demo.Domain.DTO.Product;import com.example.demo.enums.TipoAlmacenamiento;

import java.time.LocalDate;import java.util.List;

public interface ProductRepository {
    List<Product> showAll();
    Product showById(Long id);
    Product create(Product product);
    List<Product>  showByStorageType(TipoAlmacenamiento tipoAlmacenamiento);
    Product update(Long id, Product product);
    Product delete(Long id);
    List<Product> showAllExpired(LocalDate expirationDate);
    List<Product> showExpiringSoon(Long days);
}
