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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@ModelAttribute ProductRequest productRequest) throws ProductDuplicateException, IOException {
        Product result = productService.createProduct(productRequest);
        return ResponseEntity.created(URI.create("/products/" + result.getId())).body(result);
    }
    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getProductImage(@PathVariable Long id) throws IOException {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

<<<<<<< Updated upstream
        String imageUrl = product.getImageUrl();
        if (imageUrl == null) {
            return ResponseEntity.notFound().build();
        }

        Path filePath = Paths.get("C:/Users/Panchi/Desktop/APIS-Grupo-4-Backend/demo/product-images").resolve(imageUrl.substring(imageUrl.lastIndexOf("/") + 1));

        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(filePath);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateProductAttribute(@PathVariable Long productId, @RequestBody ProductRequest updatedAttributes) {
        try {
            Product updatedProduct = productService.updateProductAttribute(productId, updatedAttributes);
            return ResponseEntity.ok(updatedProduct);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
=======
    @GetMapping()
    public ResponseEntity<Object> buscarTodosProducts(){
        List<Product> result = productService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> buscarUnicoProducto(@PathVariable Long productId){
        Product result = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<Object> buscarProductosPorCategoria(@PathVariable String categoryName){
        List<Product> result = productService.getProductByCategory(categoryName);
        return ResponseEntity.status(HttpStatus.OK).body(result);
>>>>>>> Stashed changes
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        String message = "Product with ID " + productId + " has been deleted successfully.";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateProductAttribute(@PathVariable Long productId, @ModelAttribute ProductRequest updatedAttributes) throws IOException {
        Product result = productService.updateProductAttribute(productId, updatedAttributes);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
