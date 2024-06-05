package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String name;

    @Column(name = "PRODUCT_DESCRIPTION", nullable = false)
    private String description;

    @Min(value = 0, message = "El valor del precio no puede ser menor a 0")
    @Column(name = "PRODUCT_PRICE", nullable = false)
    private float price;

<<<<<<< Updated upstream
    @Column(name = "PRODUCT_IMAGE_URL")
    private String imageUrl;

    @Column(name = "PRODUCT_CATEGORY")
=======
    @Column(name = "PRODUCT_CATEGORY", nullable = false)
>>>>>>> Stashed changes
    private String category;

    @Min(value = 0, message = "El valor de stock no puede ser menor a 0")
    @Column(name = "PRODUCT_STOCK", nullable = false)
    private int stock;

}
