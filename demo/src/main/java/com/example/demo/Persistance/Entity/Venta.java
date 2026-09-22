package com.example.demo.Persistance.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "sold_by")
    private Usuario vendidoPor;
    @Column(name = "quantity")
    private Integer cantidad;
    @Column(name = "sale_date")
    private LocalDateTime fechaVendido;
}
