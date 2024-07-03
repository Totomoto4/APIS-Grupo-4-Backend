package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.ProductRequest;
import com.uade.tpo.demo.entity.dto.ProductWithImage;
import com.uade.tpo.demo.exceptions.ImageNotAvailableException;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;
import com.uade.tpo.demo.exceptions.ProductNotFoundException;
import com.uade.tpo.demo.service.ImageService;
import com.uade.tpo.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;

    @GetMapping("/auth/products")
    public ResponseEntity<List<ProductWithImage>> buscarTodosProducts() throws ImageNotAvailableException {
        List<Product> productos = productService.buscarTodosProducts();
        List<ProductWithImage> productWithImageList = new ArrayList<>();
        for (Product product : productos){
            byte[] image = imageService.getImage(product.getId());
            ProductWithImage productWithImage = new ProductWithImage(product, image);
            productWithImageList.add(productWithImage);
        }
        return ResponseEntity.status(HttpStatus.OK).body(productWithImageList);
    }

    @GetMapping("/auth/products/{productId}")
    public ResponseEntity<ProductWithImage> buscarUnicoProducto(@PathVariable Long productId) throws ImageNotAvailableException {
        Product product = productService.getProductById(productId);
        byte[] image = imageService.getImage(product.getId());
        ProductWithImage productWithImage = new ProductWithImage(product, image);
        return ResponseEntity.status(HttpStatus.OK).body(productWithImage);
    }

    @GetMapping("auth/products/category/{categoryName}")
    public ResponseEntity<List<ProductWithImage>> buscarProductosPorCategoria(@PathVariable String categoryName) throws ImageNotAvailableException {
        List<Product> productos = productService.getProductByCategory(categoryName);
        List<ProductWithImage> productWithImageList = new ArrayList<>();
        for (Product product : productos){
            byte[] image = imageService.getImage(product.getId());
            ProductWithImage productWithImage = new ProductWithImage(product, image);
            productWithImageList.add(productWithImage);
        }
        return ResponseEntity.status(HttpStatus.OK).body(productWithImageList);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@ModelAttribute ProductRequest productRequest) throws ProductDuplicateException, IOException {
        System.out.println(productRequest);
        Product result = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/products/{productId}")
    public ResponseEntity<Object> updateProductAttribute(@PathVariable Long productId, @ModelAttribute ProductRequest updatedAttributes) throws IOException {
        Product result = productService.updateProductAttribute(productId, updatedAttributes);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        String message = "Product with ID " + productId + " has been deleted successfully.";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
