package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductOrdered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ORDERED_ID")
    private long productOrderedId;

    @Column(name = "PRODUCT_ORDERED_ORDER_ID")
    private long orderId;

    @Column(name = "PRODUCT_ORDERED_NAME")
    private String name;

    @Column(name = "PRODUCT_ORDERED_PRICE")
    private float price;

    @Column(name = "PRODUCT_ORDERED_QUANTITY")
    private int quantity;

    public ProductOrdered(long orderId, String name, float price, int quantity) {
        this.orderId = orderId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
