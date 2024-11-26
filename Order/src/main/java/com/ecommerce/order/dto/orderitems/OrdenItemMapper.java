package com.ecommerce.order.dto.orderitems;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ecommerce.order.models.OrdenItem;

@Mapper(componentModel = "spring")
public interface OrdenItemMapper {
    OrdenItemMapper INSTANCE = Mappers.getMapper(OrdenItemMapper.class);

    OrdenItemDto toDto(OrdenItem ordenItem);
    OrdenItem toEntity(OrdenItemDto ordenItemDto);
    OrdenItem toSaveDto(OrdenItemToSaveDto ordenItemToSaveDto);
}
