package com.ecommerce.product.service;

import java.util.List;
import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.dto.ProductToSaveDto;

public interface ProductService {
    ProductDto save(ProductToSaveDto productDto);
    ProductDto update(Long id, ProductToSaveDto productDto);
    ProductDto findById(Long id);
    List<ProductDto> findAll();
    void deleteById(Long id);
}
