package com.ecommerce.product.dto;

public record ProductToSaveDto(Long id, String name, String description, double price, Integer stock) {
}
