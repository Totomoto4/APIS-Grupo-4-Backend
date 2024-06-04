package com.uade.tpo.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadResponse {
    private String name; // The name of the uploaded image
    private String type; // The type of the uploaded image (e.g., PNG, JPEG)
    private long size;   // The size of the uploaded image in bytes
    private String message; // A message indicating the status of the upload

    public ImageUploadResponse(String s) {
    }
}
