    package com.ecommerce.order.service.orderitem;


    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

import com.ecommerce.order.client.ProductDto;
import com.ecommerce.order.client.ProductFeignClient;
import com.ecommerce.order.dto.OrdenToSaveDto;
    import com.ecommerce.order.dto.orderitems.OrdenItemDto;
    import com.ecommerce.order.dto.orderitems.OrdenItemMapper;
import com.ecommerce.order.dto.orderitems.OrdenItemToSaveDto;
import com.ecommerce.order.exception.NotFoundExceptionEntity;
    import com.ecommerce.order.models.Orden;
    import com.ecommerce.order.models.OrdenItem;
    import com.ecommerce.order.repository.OrdenItemRepository;

    import java.util.List;

    @Service
    public class OrdenItemServiceImpl implements OrdenItemService{

        private final OrdenItemRepository ordenItemRepository;
        private final OrdenItemMapper ordenItemMapper;
        private final ProductFeignClient productFeignClient;


        @Autowired
        public OrdenItemServiceImpl(OrdenItemRepository ordenItemRepository, OrdenItemMapper ordenItemMapper, ProductFeignClient productFeignClient) {
            this.ordenItemRepository = ordenItemRepository;
            this.ordenItemMapper = ordenItemMapper;
            this.productFeignClient = productFeignClient;
        }

        @Override
        public void save(Orden order, OrdenToSaveDto ordenDto) {

            ordenDto.orderItems().forEach(ordenItemToSaveDto -> {
                    OrdenItem ordenItem = ordenItemMapper.toSaveDto(ordenItemToSaveDto);
                    ordenItem.setOrder(order);
                    Double total = getOrderItemTotal(ordenItemToSaveDto);
                    System.out.println("Total: " + total);
                    ordenItem.setTotal(total);
                    ordenItemMapper.toDto(ordenItemRepository.save(ordenItem));
            });
            
        }

        private Double getOrderItemTotal(OrdenItemToSaveDto ordenItem) {
            ProductDto product = findProductById(ordenItem.productId());
            return product.price() * ordenItem.quantity();     
        }


        @Override
        public OrdenItemDto findById(Long id) {
            return ordenItemRepository.findById(id)
                    .map(ordenItemMapper::toDto)
                    .orElseThrow(() -> new NotFoundExceptionEntity("OrdenItem not found"));
        }

        @Override
        public List<OrdenItemDto> findAll() {
            return ordenItemRepository.findAll().stream()
                    .map(ordenItemMapper::toDto)
                    .toList();
        }

        @Override
        public void deleteById(Long id) {
            OrdenItem ordenItem = ordenItemRepository.findById(id)
                    .orElseThrow(() -> new NotFoundExceptionEntity("OrdenItem not found"));

            ordenItemRepository.delete(ordenItem);

        }

        @Override
    public ProductDto findProductById(Long id) {
        return productFeignClient.findById(id);
    }


    }
