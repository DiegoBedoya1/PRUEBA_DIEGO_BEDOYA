package com.example.demo.Persistance.Crud;

import com.example.demo.Persistance.Entity.Producto;
import com.example.demo.enums.TipoAlmacenamiento;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductoCrudRepository extends CrudRepository<Producto, Long> {
    List<Producto> findAllByTipoAlmacenamiento(TipoAlmacenamiento tipoAlmacenamiento);
    List<Producto> findAllByFechaExpiracionBefore(LocalDate fechaExpiracion);
    List<Producto> findAllByFechaExpiracionBetween(LocalDate inicio, LocalDate fin);
}
