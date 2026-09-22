package com.example.demo.Domain.Repository;

import com.example.demo.Domain.DTO.Sale;

import java.util.List;

public interface SaleRepository {
    Sale register(Sale sale);
    List<Sale> showAllByUser(Long id);
    Sale showById(Long id);
}
