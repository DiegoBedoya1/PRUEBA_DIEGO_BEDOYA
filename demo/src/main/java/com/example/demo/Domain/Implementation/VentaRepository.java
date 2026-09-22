package com.example.demo.Domain.Implementation;

import com.example.demo.Domain.DTO.Sale;
import com.example.demo.Domain.Repository.SaleRepository;
import com.example.demo.Persistance.Crud.VentaCrudRepository;
import com.example.demo.Persistance.Entity.Venta;
import com.example.demo.Persistance.Mapper.VentaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class VentaRepository implements SaleRepository {
    private final VentaCrudRepository crud;
    private final VentaMapper mapper;

    @Override
    public Sale register(Sale sale){
        if(sale.getQuantity() <=0 ){
            throw new RuntimeException("La cantidad no puede ser 0 o negativo");
        }
        Sale sale1 = new Sale(null,
                sale.getProduct(),
                sale.getSoldBy(),
                sale.getQuantity(),
                sale.getSaleDate()
        );
        Venta guardado = crud.save(mapper.toVenta(sale1));
        return mapper.toSale(guardado);
    }

    @Override
    public List<Sale> showAllByUser(Long id){
        return mapper.toSales(crud.findAllByVendidoPorId(id));
    }

    @Override
    public Sale showById(Long id){
        Venta venta = crud.findById(id)
                .orElseThrow(() -> new RuntimeException("La venta no existe"));
        return mapper.toSale(venta);
    }
}
