package com.ecommerce.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductDto> save(ProductToSaveDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        try {
            ProductDto productDto = productService.findById(id);
            return ResponseEntity.ok(productDto);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, ProductToSaveDto productDto) {
        try {
            ProductDto productDtoUpdated = productService.update(id, productDto);
            return ResponseEntity.ok(productDtoUpdated);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }


}
