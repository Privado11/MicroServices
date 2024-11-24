package com.ecommerce.order.service;

import java.util.List;

import com.ecommerce.order.client.ProductDto;
import com.ecommerce.order.dto.OrdenDto;
import com.ecommerce.order.dto.OrdenToSaveDto;

public interface OrdenService {
    OrdenDto save(OrdenToSaveDto ordenDto);
    OrdenDto update(Long id, OrdenToSaveDto ordenDto);
    OrdenDto findById(Long id);
    List<OrdenDto> findAll();
    void deleteById(Long id);
    OrdenDto updateStatus(Long id, String status);
    ProductDto findProductById(Long id);
    List<ProductDto> findAllProducts();
}
