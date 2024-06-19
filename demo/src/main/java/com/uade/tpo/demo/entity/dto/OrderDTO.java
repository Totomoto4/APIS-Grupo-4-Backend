package com.uade.tpo.demo.entity.dto;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.ProductOrdered;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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
