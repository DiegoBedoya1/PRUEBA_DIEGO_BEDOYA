package com.example.demo.web.Controller;

import com.example.demo.Domain.DTO.Sale;
import com.example.demo.Domain.Service.SaleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class SaleController {
    private final SaleService service;

    @PostMapping("/register")
    public ResponseEntity<Sale> register(@RequestBody Sale sale){
        return ResponseEntity.ok(service.register(sale));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Sale>> showAllByUser(@PathVariable Long id, Authentication authentication){
        return ResponseEntity.ok(service.showAllByUser(id, authentication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> showById(@PathVariable Long id){
        return ResponseEntity.ok(service.showById(id));
    }
}

