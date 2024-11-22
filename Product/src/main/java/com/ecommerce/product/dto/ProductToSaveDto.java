package com.ecommerce.product.dto;

public record ProductToSaveDto(Long id, String name, String description, Double price, Integer stock) {
}
