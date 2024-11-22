package com.ecommerce.order.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ordens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

}
