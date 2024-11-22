package com.ecommerce.order.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<OrdenDto> save(OrdenToSaveDto ordenDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.save(ordenDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDto> findById(@PathVariable Long id) {
        try {
            OrdenDto ordenDto = ordenService.findById(id);
            return ResponseEntity.ok(ordenDto);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenDto> update(@PathVariable Long id, OrdenToSaveDto ordenDto) {
        try {
            OrdenDto ordenDtoUpdated = ordenService.update(id, ordenDto);
            return ResponseEntity.ok(ordenDtoUpdated);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            ordenService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrdenDto> updateStatus(@PathVariable Long id, String status) {
        try {
            OrdenDto ordenDtoUpdated = ordenService.updateStatus(id, status);
            return ResponseEntity.ok(ordenDtoUpdated);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
