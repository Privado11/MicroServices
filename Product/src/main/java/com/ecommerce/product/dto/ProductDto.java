package com.ecommerce.product.dto;

public record ProductDto(Long id,
                         String name,
                         String description,
                         double price,
                         Integer stock) {
}