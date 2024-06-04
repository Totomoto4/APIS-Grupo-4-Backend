package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "products_ordered")
public class ProductOrdered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ORDERED_ID")
    private Long productOrderedId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ORDERED_ORDER_ID", nullable = false)
    private Order order;

    @Column(name = "PRODUCT_ORDERED_NAME")
    private String name;

    @Column(name = "PRODUCT_ORDERED_PRICE")
    private float price;

    @Column(name = "PRODUCT_ORDERED_QUANTITY")
    private int quantity;

}
