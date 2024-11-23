package com.ecommerce.order.dto.orderitems;

public record OrdenItemDto(
    Long id, 
    Long productId,
    Double price,
    Integer quantity) {
}
