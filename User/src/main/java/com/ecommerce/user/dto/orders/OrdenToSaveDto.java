package com.ecommerce.user.dto.orders;

public record OrdenToSaveDto(Long id, String name, String description, String status, Long userId) {
}
