package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private int id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;

    @Column(name = "PRODUCT_PRICE")
    private float price;

    @Column(name = "PRODUCT_IMAGE_URL")
    private String imageUrl;

    @Column(name = "PRODUCT_CATEGORY")
    private String category;

    @Column(name = "PRODUCT_STOCK")
    private int stock;

}
