package com.ecommerce.order.dto;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ecommerce.order.models.Orden;

@Mapper(componentModel = "spring")
public interface OrdenMapper {
    OrdenMapper INSTANCE = Mappers.getMapper(OrdenMapper.class);

    OrdenDto toDto(Orden orden);
    Orden toEntity(OrdenDto ordenDto);
    Orden toSaveDto(OrdenToSaveDto ordenToSaveDto);
}
