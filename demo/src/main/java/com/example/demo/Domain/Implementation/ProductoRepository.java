package com.example.demo.Domain.Implementation;

import com.example.demo.Domain.DTO.Product;
import com.example.demo.Domain.Repository.ProductRepository;
import com.example.demo.Persistance.Crud.ProductoCrudRepository;
import com.example.demo.Persistance.Entity.Producto;
import com.example.demo.Persistance.Mapper.ProductoMapper;
import com.example.demo.enums.TipoAlmacenamiento;import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;import java.util.List;

@Repository
@AllArgsConstructor
public class ProductoRepository implements ProductRepository {
    private final ProductoCrudRepository crud;
    private final ProductoMapper mapper;

    @Override
    public List<Product> showAll(){
        return mapper.toProducts((List<Producto>)crud.findAll());
    }

    @Override
    public Product showById(Long id){
        return mapper.toProduct(crud.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no existe")));
    }

    @Override
    public Product create(Product product){
        if(product.getPrice() <= 0 ){
            throw new RuntimeException("El producto no puede tener un precio negativo o 0");
        }
        if(product.getStock() < 0){
            throw new RuntimeException("El stock no puede ser negativo");
        }

        if(product.getName() == null){
            throw new RuntimeException("El nombre no puede estar vacio");
        }

        if(product.getStorageType() == null){
            throw new RuntimeException("El tipo de almacenamiento no debe de ser negativo");
        }

        Product product1 = new Product(null,
                product.getName(),
                product.getActiveIngredient(),
                product.getStorageType(),
                product.getExpirationDate(),
                product.getPrice(),
                product.getStock(),
                product.getRequiresPreescription()
        );
        Producto guardado = crud.save(mapper.toProducto(product1));
        return mapper.toProduct(guardado);
    }

    @Override
    public List<Product> showByStorageType(TipoAlmacenamiento tipoAlmacenamiento){
        return mapper.toProducts(crud.findAllByTipoAlmacenamiento(tipoAlmacenamiento));
    }

    @Override
    public Product update(Long id, Product product){
        if(product.getPrice() <= 0 ){
            throw new RuntimeException("El producto no puede tener un precio negativo o 0");
        }
        if(product.getStock() < 0){
            throw new RuntimeException("El stock no puede ser negativo");
        }

        if(product.getName() == null){
            throw new RuntimeException("El nombre no puede estar vacio");
        }

        if(product.getStorageType() == null){
            throw new RuntimeException("El tipo de almacenamiento no debe de ser negativo");
        }
        Producto producto = crud.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no existe"));
        producto.setNombre(product.getName());
        producto.setIngredienteActivo(product.getActiveIngredient());
        producto.setFechaExpiracion(product.getExpirationDate());
        producto.setPrecio(product.getPrice());
        producto.setStock(product.getStock());
        producto.setTipoAlmacenamiento(product.getStorageType());
        producto.setRequierePreescripcion(product.getRequiresPreescription());
        return mapper.toProduct(crud.save(producto));
    }

    @Override
    public Product delete(Long id){
        Producto producto = crud.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no existe"));
        crud.delete(producto);
        return mapper.toProduct(producto);
    }

    @Override
    public List<Product> showAllExpired(LocalDate fechaExpiracion){
        return mapper.toProducts(crud.findAllByFechaExpiracionBefore(fechaExpiracion));
    }

    @Override
    public List<Product> showExpiringSoon(Long days){
        if(days <= 0){
            throw new RuntimeException("El dia no puede ser negativo o 0");
        }
        LocalDate hora = LocalDate.now().plusDays(days);
        List<Producto> proximosExpirar = crud.findAllByFechaExpiracionBetween(LocalDate.now(),hora);
        return mapper.toProducts(proximosExpirar);
    }
}
