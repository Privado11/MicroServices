package com.ecommerce.order.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String status;

    @Column
    private Double totalOrder;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrdenItem> orderItems;


}
