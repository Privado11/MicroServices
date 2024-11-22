package com.ecommerce.user.dto.orders;

public record OrdenDto(Long id, String name, String description, String status, Long userId) {
}
