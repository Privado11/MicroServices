package com.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.models.OrdenItem;

public interface OrdenItemRepository extends JpaRepository<OrdenItem, Long> {

}
