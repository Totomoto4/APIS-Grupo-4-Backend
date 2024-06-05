package com.uade.tpo.demo.entity;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private float price;
    ProductDTO(ProductOrdered product){
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
