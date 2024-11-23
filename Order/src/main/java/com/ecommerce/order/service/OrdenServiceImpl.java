package com.ecommerce.order.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.dto.OrdenDto;
import com.ecommerce.order.dto.OrdenMapper;
import com.ecommerce.order.dto.OrdenToSaveDto;
import com.ecommerce.order.exception.NotFoundExceptionEntity;
import com.ecommerce.order.models.Orden;

import com.ecommerce.order.repository.OrdenRepository;
import com.ecommerce.order.service.orderitem.OrdenItemService;

import java.util.List;

@Service
public class OrdenServiceImpl implements OrdenService{

    private final OrdenRepository ordenRepository;
    private final OrdenMapper ordenMapper;

    private final OrdenItemService ordenItemService;

    @Autowired
    public OrdenServiceImpl(OrdenRepository ordenRepository, OrdenMapper ordenMapper, OrdenItemService ordenItemService) {
        this.ordenRepository = ordenRepository;
        this.ordenMapper = ordenMapper;
        this.ordenItemService = ordenItemService;
    }

    @Override
    public OrdenDto save(OrdenToSaveDto ordenDto) {
        Orden orden = ordenMapper.toSaveDto(ordenDto);
        Orden ordenSaved = ordenRepository.save(orden);
        ordenItemService.save(ordenSaved, ordenDto);
        return ordenMapper.toDto(ordenSaved);
        
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
        return ordenRepository.findById(id)
                .map(ordenMapper::toDto)
                .orElseThrow(() -> new NotFoundExceptionEntity("Orden not found"));
    }

    @Override
    public List<OrdenDto> findAll() {
        return ordenRepository.findAll().stream()
                .map(ordenMapper::toDto)
                .toList();
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
}
