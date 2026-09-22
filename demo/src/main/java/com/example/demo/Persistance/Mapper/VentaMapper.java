package com.example.demo.Persistance.Mapper;

import com.example.demo.Domain.DTO.Sale;
import com.example.demo.Persistance.Entity.Venta;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class, UsuarioMapper.class})
public interface VentaMapper {

    @Mappings({
            @Mapping(source = "producto", target = "product"),
            @Mapping(source = "vendidoPor", target = "soldBy"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "fechaVendido", target = "saleDate")
    })
    Sale toSale(Venta venta);

    @InheritInverseConfiguration
    Venta toVenta(Sale sale);

    List<Sale> toSales(List<Venta> ventas);
    List<Venta> toVentas(List<Sale> sale);
}
