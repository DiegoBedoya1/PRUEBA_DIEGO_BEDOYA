package com.example.demo.web.Controller;

import com.example.demo.Domain.DTO.Product;
import com.example.demo.Domain.Service.ProductService;
import com.example.demo.enums.TipoAlmacenamiento;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/products")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> showAll(){
        return ResponseEntity.ok(service.showAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> showById(@PathVariable Long id){
        return ResponseEntity.ok(service.showById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<Product> create(@RequestBody Product product){
        return ResponseEntity.ok(service.create(product));
    }

    @GetMapping("/storage/{storageType}")
    public ResponseEntity<List<Product>> showByStorageType(@PathVariable TipoAlmacenamiento storageType){
        return ResponseEntity.ok(service.showByStorageType(storageType));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product){
        return ResponseEntity.ok(service.update(id,product));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/all/expired")
    public ResponseEntity<List<Product>> showAllExpired(){
        return ResponseEntity.ok(service.showAllExpired(LocalDate.now()));
    }

    @GetMapping("/expiring-soon/{days}")
    public ResponseEntity<List<Product>> showExpiringSoon(@PathVariable Long days){
        return ResponseEntity.ok(service.showExpiringSoon(days));
    }

}
