package com.ecommerce.order.dto.products;

public record ProductDto(Long id,
                         String name,
                         String description,
                         double price,
                         Integer stock) {
}
