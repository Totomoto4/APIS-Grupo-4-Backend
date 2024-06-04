package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
@Builder
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "ORDER_DATE_TIME")
    private LocalDateTime timeOfPurchase;

    @Column(name = "ORDER_TOTAL")
    private float total;

    @Column(name = "ORDER_CARD_NUMBER")
    private long cardNumber;

    @Column(name = "ORDER_ADDRESS")
    private String address;

    @Transient
    private HashMap<Product, Integer> products;

}
