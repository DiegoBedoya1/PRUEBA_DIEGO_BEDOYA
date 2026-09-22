package com.example.demo.Persistance.Entity;

import com.example.demo.enums.TipoAlmacenamiento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String nombre;
    @Column(name = "active_ingredient")
    private String ingredienteActivo;
    @Enumerated(EnumType.STRING)
    @Column(name = "storage_type")
    private TipoAlmacenamiento tipoAlmacenamiento;
    @Column(name = "expiration_date")
    private LocalDate fechaExpiracion;
    @Column(name = "price")
    private Double precio;
    private Integer stock;
    @Column(name = "requires_prescription")
    private Boolean requierePreescripcion;
}
