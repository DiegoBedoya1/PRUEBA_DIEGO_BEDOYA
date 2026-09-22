package com.example.demo.Domain.DTO;

import com.example.demo.enums.TipoAlmacenamiento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String activeIngredient;
    private TipoAlmacenamiento storageType;
    private LocalDate expirationDate;
    private Double price;
    private Integer stock;
    private Boolean requiresPreescription;
}
