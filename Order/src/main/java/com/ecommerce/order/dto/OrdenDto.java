package com.ecommerce.order.dto;

import java.util.List;

import com.ecommerce.order.dto.orderitems.OrdenItemDto;

public record OrdenDto(
    Long id, 
    Long userId,
    Double totalOrder,
    String status, 
    List<OrdenItemDto> orderItems) {

        public List<OrdenItemDto> orderItem() {
            if (orderItems == null) {
                return List.of();
            }

            return List.copyOf(orderItems);
        }
}
