package com.example.demo.Persistance.Mapper;

import com.example.demo.Domain.DTO.Product;
import com.example.demo.Persistance.Entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "ingredienteActivo", target = "activeIngredient"),
            @Mapping(source = "tipoAlmacenamiento", target = "storageType"),
            @Mapping(source = "fechaExpiracion", target = "expirationDate"),
            @Mapping(source = "precio", target = "price"),
            @Mapping(source = "requierePreescripcion", target = "requiresPreescription")
    })
    Product toProduct(Producto producto);

    @InheritInverseConfiguration
    Producto toProducto(Product product);

    List<Product> toProducts(List<Producto> productos);
    List<Producto> toProductos(List<Product> products);
}
