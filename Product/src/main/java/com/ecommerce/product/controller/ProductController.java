package com.ecommerce.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.dto.ProductToSaveDto;
import com.ecommerce.product.exception.NotFoundExceptionEntity;
import com.ecommerce.product.service.ProductService;



@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody ProductToSaveDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id) {
        try {
            ProductDto productDto = productService.findById(id);
            return ResponseEntity.ok(productDto);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable("id") Long id, @RequestBody ProductToSaveDto productDto) {
        try {
            ProductDto productDtoUpdated = productService.update(id, productDto);
            return ResponseEntity.ok(productDtoUpdated);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }


}
