package com.ecommerce.product.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.dto.ProductMapper;
import com.ecommerce.product.dto.ProductToSaveDto;
import com.ecommerce.product.exception.NotAbleToDeleteException;
import com.ecommerce.product.exception.NotFoundExceptionEntity;
import com.ecommerce.product.models.Product;
import com.ecommerce.product.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto save(ProductToSaveDto productDto) {
        Product product = productMapper.toSaveDto(productDto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto update(Long id, ProductToSaveDto productDto) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDto.name());
                    product.setDescription(productDto.description());
                    product.setPrice(productDto.price());
                    product.setStock(productDto.stock());

                    return productMapper.toDto(productRepository.save(product));
                })
                .orElseThrow(() -> new NotFoundExceptionEntity("Product not found"));
    }

    @Override
    public ProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new NotFoundExceptionEntity("Product not found"));
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("Product not found"));

        productRepository.delete(product);

    }
}
