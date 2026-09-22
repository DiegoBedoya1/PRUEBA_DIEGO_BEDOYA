package com.example.demo.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    private Long id;
    private Product product;
    private User soldBy;
    private Integer quantity;
    private LocalDateTime saleDate;
}
