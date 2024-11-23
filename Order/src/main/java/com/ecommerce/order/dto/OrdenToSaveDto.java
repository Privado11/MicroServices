package com.ecommerce.order.dto;

import java.util.List;

import com.ecommerce.order.dto.orderitems.OrdenItemToSaveDto;

public record OrdenToSaveDto(
    Long id, 
    Long userId,
    String status, 
    List<OrdenItemToSaveDto> orderItems) {
}
