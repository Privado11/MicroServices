package com.ecommerce.order.dto.orderitems;

public record OrdenItemToSaveDto(
    Long id, 
    Long productId,
    Double total,
    Integer quantity) {
}
