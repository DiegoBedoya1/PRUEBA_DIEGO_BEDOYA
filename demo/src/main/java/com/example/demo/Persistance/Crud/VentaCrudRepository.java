package com.example.demo.Persistance.Crud;

import com.example.demo.Persistance.Entity.Venta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VentaCrudRepository extends CrudRepository<Venta, Long> {
    List<Venta> findAllByVendidoPorId(Long id);
}
