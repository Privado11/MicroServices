package com.ecommerce.order.service.orderitem;
import java.util.List;

import com.ecommerce.order.client.ProductDto;
import com.ecommerce.order.dto.OrdenToSaveDto;
import com.ecommerce.order.dto.orderitems.OrdenItemDto;
import com.ecommerce.order.models.Orden;

public interface OrdenItemService {
    void save(Orden order, OrdenToSaveDto OrdenItemDto);
    OrdenItemDto findById(Long id);
    List<OrdenItemDto> findAll();
    void deleteById(Long id);
    ProductDto findProductById(Long id);
}
