package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
<<<<<<< Updated upstream
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
>>>>>>> Stashed changes
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
<<<<<<< Updated upstream
=======
@Builder
@AllArgsConstructor
@NoArgsConstructor
>>>>>>> Stashed changes
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private long id;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "ORDER_DATE_TIME", nullable = false)
    private LocalDateTime timeOfPurchase;

    @Column(name = "ORDER_TOTAL", nullable = false)
    private float total;

    @Column(name = "ORDER_CARD_NUMBER", nullable = false)
    private long cardNumber;

    @Column(name = "ORDER_ADDRESS", nullable = false)
    private String address;

    @Transient
    private HashMap<Product, Integer> products;

    public Order() {
    }

    public Order(int userId, LocalDateTime timeOfPurchase, float total, long cardNumber, String address, HashMap<Product, Integer> products) {
        this.userId = userId;
        this.timeOfPurchase = timeOfPurchase;
        this.total = total;
        this.cardNumber = cardNumber;
        this.address = address;
        this.products = products;
    }
}
