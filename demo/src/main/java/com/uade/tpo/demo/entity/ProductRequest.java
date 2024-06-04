package com.uade.tpo.demo.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private float price;
    private MultipartFile image;
    private String category;
    private int stock;
}