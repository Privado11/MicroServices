package com.ecommerce.order.dto.products;

public record ProductToSaveDto(Long id, String name, String description, double price, Integer stock) {
}
