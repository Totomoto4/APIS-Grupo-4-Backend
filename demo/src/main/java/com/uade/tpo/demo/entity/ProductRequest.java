package com.uade.tpo.demo.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequest {
    private Long id;
    private String name;
    private String description;
<<<<<<< Updated upstream
    private float price;
    private MultipartFile imageUrl;
=======
    private Float price;
    private MultipartFile image;
>>>>>>> Stashed changes
    private String category;
    private Integer stock;
}