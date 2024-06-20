package com.uade.tpo.demo.entity.dto;

import com.uade.tpo.demo.entity.Product;
import lombok.Data;

@Data
public class ProductWithImage {
    private Long id;
    private String name;
    private String description;
    private float price;
    private String category;
    private int stock;
    private byte[] imageData;

    public ProductWithImage(Product product, byte[] imageData) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.stock = product.getStock();
        this.imageData = imageData;
    }
}
