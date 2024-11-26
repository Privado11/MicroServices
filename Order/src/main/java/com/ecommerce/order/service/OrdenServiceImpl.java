package com.ecommerce.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.client.ProductDto;
import com.ecommerce.order.client.ProductFeignClient;
import com.ecommerce.order.dto.OrdenDto;
import com.ecommerce.order.dto.OrdenMapper;
import com.ecommerce.order.dto.OrdenToSaveDto;
import com.ecommerce.order.exception.NotFoundExceptionEntity;
import com.ecommerce.order.models.Orden;
import com.ecommerce.order.models.OrdenItem;
import com.ecommerce.order.repository.OrdenRepository;
import com.ecommerce.order.service.orderitem.OrdenItemService;

import feign.FeignException;

import java.util.List;

@Service
public class OrdenServiceImpl implements OrdenService{

    private final OrdenRepository ordenRepository;
    private final OrdenMapper ordenMapper;

    private final OrdenItemService ordenItemService;
    
    private final ProductFeignClient productFeignClient;

    @Autowired
    public OrdenServiceImpl(OrdenRepository ordenRepository, OrdenMapper ordenMapper, OrdenItemService ordenItemService, ProductFeignClient productFeignClient) {
        this.ordenRepository = ordenRepository;
        this.ordenMapper = ordenMapper;
        this.ordenItemService = ordenItemService;
        this.productFeignClient = productFeignClient;
    }

    @Override
    public OrdenDto save(OrdenToSaveDto ordenDto) {
        Orden orden = ordenMapper.toSaveDto(ordenDto);

        orden.getOrderItems().forEach(item -> {
            try {
                ProductDto product = findProductById(item.getProductId());
                if(product == null) {
                    throw new RuntimeException("Producto con ID " + item.getProductId() + " no encontrado");
                }
                if (product.stock() < item.getQuantity()) {
                    throw new RuntimeException("Producto con ID " + item.getProductId() + " no tiene suficiente stock");
                }
            } catch (FeignException.NotFound e) {
                throw new NotFoundExceptionEntity("Producto con ID " + item.getProductId() + " no encontrado");   
            }
        });

        Orden ordenSaved = ordenRepository.save(orden);
        ordenItemService.save(ordenSaved, ordenDto);
        return ordenMapper.toDto(setOrdenItem(ordenSaved));
        
    }


    @Override
    public OrdenDto update(Long id, OrdenToSaveDto ordenDto) {
        return ordenRepository.findById(id)
                .map(orden -> {
                    orden.setStatus(ordenDto.status());

                    return ordenMapper.toDto(ordenRepository.save(orden));
                })
                .orElseThrow(() -> new NotFoundExceptionEntity("Orden not found"));
    }

    @Override
    public OrdenDto findById(Long id) {
        Orden orden = ordenRepository.findById(id)
            .orElseThrow(() -> new NotFoundExceptionEntity("Orden not found"));
        return ordenMapper.toDto(setOrdenItem(orden));
    }
    
   private Orden setOrdenItem(Orden orden) {
    Double totalOrder = 0.0;

    for (OrdenItem item : orden.getOrderItems()) {
        try {
            ProductDto product = findProductById(item.getProductId());

            item.setProductName(product.name());
            item.setProductDescription(product.description());
            item.setUnitPrice(product.price());

            Double itemTotal = product.price() * item.getQuantity();
            totalOrder += itemTotal;
            item.setTotal(itemTotal);
        } catch (FeignException.NotFound e) {
            throw new NotFoundExceptionEntity("Producto con ID " + item.getProductId() + " no encontrado");
        }
    }

    orden.setTotalOrder(totalOrder);
    return orden;
}


    @Override
    public List<OrdenDto> findAll() {
        return ordenRepository.findAll().stream()
                .peek(this::fetchProducts)
                .map(ordenMapper::toDto)
                .toList();
    }

    private void fetchProducts(Orden orden) {
        orden.getOrderItems().forEach(item -> {
            try {
                ProductDto product = findProductById(item.getProductId());
                item.setProductName(product.name());
                item.setProductDescription(product.description());
                item.setUnitPrice(product.price());
            } catch (FeignException.NotFound e) {
                throw new NotFoundExceptionEntity("Producto con ID " + item.getProductId() + " no encontrado");   
            }
        });
    }


    @Override
    public void deleteById(Long id) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionEntity("Orden not found"));

        ordenRepository.delete(orden);

    }

    @Override
    public OrdenDto updateStatus(Long id, String status) {
        return ordenRepository.findById(id)
                .map(orden -> {
                    orden.setStatus(status);

                    return ordenMapper.toDto(ordenRepository.save(orden));
                })
                .orElseThrow(() -> new NotFoundExceptionEntity("Orden not found"));
    }

    @Override
    public ProductDto findProductById(Long id) {
        return productFeignClient.findById(id);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return productFeignClient.findAll();
    }
}
