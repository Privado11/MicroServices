package com.ecommerce.order.client;

public record ProductDto(Long id,
                         String name,
                         String description,
                         Double price,
                         Integer stock) {
}
