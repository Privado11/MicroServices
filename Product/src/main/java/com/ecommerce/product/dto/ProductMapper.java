package com.ecommerce.product.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ecommerce.product.models.Product;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
    Product toSaveDto(ProductToSaveDto productToSaveDto);
}
