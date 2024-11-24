package com.ecommerce.order.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.order.client.ProductDto;
import com.ecommerce.order.dto.OrdenDto;
import com.ecommerce.order.dto.OrdenToSaveDto;
import com.ecommerce.order.exception.NotFoundExceptionEntity;
import com.ecommerce.order.service.OrdenService;

@RestController
@RequestMapping("api/v1/orders")
public class OrdenController {

    private final OrdenService ordenService;

    @Autowired
    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(ordenService.findAll());
    }

    @PostMapping
    public ResponseEntity<OrdenDto> save(@RequestBody OrdenToSaveDto ordenDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.save(ordenDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDto> findById(@PathVariable("id") Long id) {
        try {
            OrdenDto ordenDto = ordenService.findById(id);
            return ResponseEntity.ok(ordenDto);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenDto> update(@PathVariable("id") Long id, @RequestBody OrdenToSaveDto ordenDto) {
        try {
            OrdenDto ordenDtoUpdated = ordenService.update(id, ordenDto);
            return ResponseEntity.ok(ordenDtoUpdated);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        try {
            ordenService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrdenDto> updateStatus(@PathVariable("id") Long id, @RequestBody String status) {
        try {
            OrdenDto ordenDtoUpdated = ordenService.updateStatus(id, status);
            return ResponseEntity.ok(ordenDtoUpdated);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<ProductDto> findProductById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(ordenService.findProductById(id));
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        return ResponseEntity.ok(ordenService.findAllProducts());
    }
}
