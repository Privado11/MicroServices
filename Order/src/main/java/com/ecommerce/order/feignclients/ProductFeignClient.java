package com.ecommerce.order.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.order.dto.products.ProductDto;
import com.ecommerce.order.dto.products.ProductToSaveDto;


@FeignClient(name = "product", url = "http://localhost:8082")
@RequestMapping("/api/v1/products")
public interface ProductFeignClient {
    
    @GetMapping("/{id}")
    ResponseEntity<ProductDto> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<ProductDto>> findAll();

    @PostMapping
    ResponseEntity<ProductDto> save(@RequestBody ProductToSaveDto productDto);

    @PutMapping("/{id}")
    ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductToSaveDto productDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id);
}
