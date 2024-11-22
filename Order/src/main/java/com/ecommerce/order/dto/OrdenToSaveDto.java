package com.ecommerce.order.dto;

public record OrdenToSaveDto(Long id, String name, String description, String status, Long userId) {
}
