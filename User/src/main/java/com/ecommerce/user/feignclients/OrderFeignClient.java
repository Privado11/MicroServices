package com.ecommerce.user.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.user.dto.orders.OrdenDto;
import com.ecommerce.user.dto.orders.OrdenToSaveDto;

@FeignClient(name = "order", url = "http://localhost:8081")
@RequestMapping("/api/v1/orders")
public interface OrderFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<OrdenDto> findById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<OrdenDto> save(@RequestBody OrdenToSaveDto ordenDto);

    @PutMapping("/{id}")
    ResponseEntity<OrdenDto> update(@PathVariable Long id, @RequestBody OrdenToSaveDto ordenDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id);

    @PatchMapping("/{id}")
    ResponseEntity<OrdenDto> updateStatus(@PathVariable Long id, @RequestBody String status);
}
