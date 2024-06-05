package com.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
public class OrderRequest {

    private String userEmail;

    private float total;

    private long cardNumber;

    private String address;

    private HashMap<Long, Integer> products;

}
