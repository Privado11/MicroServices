package com.ecommerce.order.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "order_items")
@Getter
@Setter
@Builder    
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdenItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orden order;

}
