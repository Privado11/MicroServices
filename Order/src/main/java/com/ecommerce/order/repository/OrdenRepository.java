package com.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.models.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
