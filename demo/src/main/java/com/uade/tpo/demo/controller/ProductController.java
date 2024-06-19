package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.ProductRequest;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;
import com.uade.tpo.demo.exceptions.ProductNotFoundException;
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
import java.util.List;

@RestController
@RequestMapping()
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/auth/products")
    public List<Product> buscarTodosProducts(){
        return productService.buscarTodosProducts();
    }

    @GetMapping("/auth/products/{productId}")
    public ResponseEntity<Object> buscarUnicoProducto(@PathVariable Long productId){
        Product result = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/products/create")
    public ResponseEntity<Object> createProduct(@ModelAttribute ProductRequest productRequest) throws ProductDuplicateException, IOException {
        Product result = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/products/update/{productId}")
    public ResponseEntity<Object> updateProductAttribute(@PathVariable Long productId, @ModelAttribute ProductRequest updatedAttributes) throws IOException {
        Product result = productService.updateProductAttribute(productId, updatedAttributes);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/products/delete/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        String message = "Product with ID " + productId + " has been deleted successfully.";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }


    @GetMapping("auth/products/category/{categoryName}")
    public ResponseEntity<Object> buscarProductosPorCategoria(@PathVariable String categoryName) {
        List<Product> result = productService.getProductByCategory(categoryName);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
