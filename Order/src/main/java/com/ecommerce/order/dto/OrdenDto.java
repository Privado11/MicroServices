package com.ecommerce.order.dto;

public record OrdenDto(Long id, String name, String description, String status, Long userId) {
}
