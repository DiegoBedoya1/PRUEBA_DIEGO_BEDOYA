package com.example.demo.Domain.Service;

import com.example.demo.Domain.DTO.Sale;
import com.example.demo.Domain.Repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository repo;

    public Sale register(Sale sale){
        return repo.register(sale);
    }

    public List<Sale> showAllByUser(Long id){
        return repo.showAllByUser(id);
    }

    public Sale showById(Long id){
        return repo.showById(id);
    }
}
