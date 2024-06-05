package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class OrderDTO {

    private Long id;

    private String userEmail;

    private LocalDateTime timeOfPurchase;

    private float total;

    private long cardNumber;

    private String address;

    private HashMap<ProductDTO, Integer> products;

    public OrderDTO(Order order, List<ProductOrdered> productOrdereds){
        this.id = order.getId();
        this.userEmail = order.getUser().getEmail();
        this.timeOfPurchase = order.getTimeOfPurchase();
        this.total = order.getTotal();
        this.cardNumber = order.getCardNumber();
        this.address = order.getAddress();
        this.products = new HashMap<>();

        for (ProductOrdered productOrdered : productOrdereds){
            products.put(new ProductDTO(productOrdered), productOrdered.getQuantity());
        }

    }
}
