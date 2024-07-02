package com.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Data
public class OrderRequest {

    private String email;

    private LocalDateTime timeOfPurchase;

    private long cardNumber;

    private String address;

    private HashMap<Long, Integer> products;

    private List<String> codigos;
}
