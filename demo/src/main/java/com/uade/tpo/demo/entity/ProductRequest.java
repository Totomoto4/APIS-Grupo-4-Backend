package com.uade.tpo.demo.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Float price;
    private MultipartFile image;
    private String category;
    private Integer stock;
}