package com.ecommerce.product.dto;

public record ProductDto(Long id,
                         String name,
                         String description,
                         Double price,
                         Integer stock) {
}
