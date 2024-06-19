package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.entity.Image;
import com.uade.tpo.demo.entity.ImageUploadResponse;
import com.uade.tpo.demo.exceptions.ImageNotAvailableException;
import com.uade.tpo.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/image/{productId}")
    public ResponseEntity<?>  getImageByName(@PathVariable("productId") Long productId) throws ImageNotAvailableException {
        byte[] image = imageService.getImage(productId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

}