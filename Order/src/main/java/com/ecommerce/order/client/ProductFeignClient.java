package com.ecommerce.order.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "product-service", url = "http://localhost:8080")
public interface ProductFeignClient {

    @GetMapping("/api/v1/products")
    List<ProductDto> findAll();

    @GetMapping("/api/v1/products/{id}")
    ProductDto findById(@PathVariable("id") Long id);


}
