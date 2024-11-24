package com.ecommerce.order.dto.orderitems;

import com.ecommerce.order.client.ProductDto;

public record OrdenItemDto(
    Long id, 
    Long productId,
    Double price,
    Integer quantity,
    ProductDto product) {
}
