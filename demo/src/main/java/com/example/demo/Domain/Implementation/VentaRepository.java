package com.example.demo.Domain.Implementation;

import com.example.demo.Domain.DTO.Product;
import com.example.demo.Domain.DTO.Sale;
import com.example.demo.Domain.DTO.User;
import com.example.demo.Domain.Repository.SaleRepository;
import com.example.demo.Persistance.Crud.ProductoCrudRepository;
import com.example.demo.Persistance.Crud.UsuarioCrudRepository;
import com.example.demo.Persistance.Crud.VentaCrudRepository;
import com.example.demo.Persistance.Entity.Producto;
import com.example.demo.Persistance.Entity.Usuario;
import com.example.demo.Persistance.Entity.Venta;
import com.example.demo.Persistance.Mapper.ProductoMapper;import com.example.demo.Persistance.Mapper.UsuarioMapper;import com.example.demo.Persistance.Mapper.VentaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class VentaRepository implements SaleRepository {
    private final VentaCrudRepository crud;
    private final ProductoCrudRepository crudP;
    private final ProductoMapper mapperP;
    private final UsuarioCrudRepository crudU;
    private final UsuarioMapper mapperU;
    private final VentaMapper mapper;

    @Transactional
    @Override
    public Sale register(Sale sale){
        if(sale.getProduct() == null){
            throw new RuntimeException("No hay producto seleccionado");
        }

        if(sale.getQuantity() <=0 ){
            throw new RuntimeException("La cantidad no puede ser 0 o negativo");
        }
        Producto producto = crudP.findById(sale.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("El producto no existe"));
        if(producto.getFechaExpiracion().isBefore(LocalDate.now())){
            throw new RuntimeException("No se puede registrar una venta con un producto que ya expiro");
        }
        if(sale.getQuantity() > producto.getStock()){
            throw new RuntimeException("No se puede registrar la venta porque la cantidad supera al stock");
        }

        producto.setStock(producto.getStock() - sale.getQuantity());
        crudP.save(producto);

        Usuario usuario = crudU.findById(sale.getSoldBy().getId())
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));
        Sale sale1 = new Sale(null,
               mapperP.toProduct(producto),
                mapperU.toUSer(usuario),
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
