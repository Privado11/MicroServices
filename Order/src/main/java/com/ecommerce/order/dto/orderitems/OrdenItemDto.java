package com.ecommerce.order.dto.orderitems;

public record OrdenItemDto(
    Long id, 
    String productName, 
    String productDescription,
    Double unitPrice,
    Integer quantity,
    Double total
    ) {
}
