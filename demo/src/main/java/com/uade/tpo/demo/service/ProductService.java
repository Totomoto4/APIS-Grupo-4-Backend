package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Image;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.ProductRequest;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;
import com.uade.tpo.demo.exceptions.ProductNotFoundException;
import com.uade.tpo.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    public List<Product> buscarTodosProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequest productRequest) throws ProductDuplicateException, IOException {
        List<Product> products = productRepository.findByName(productRequest.getName());
        if (products.isEmpty()) {
            //Guardamos producto
            Product newProduct = new Product();
            newProduct.setName(productRequest.getName());
            newProduct.setDescription(productRequest.getDescription());
            newProduct.setPrice(productRequest.getPrice());
            newProduct.setCategory(productRequest.getCategory());
            newProduct.setStock(productRequest.getStock());
            Product savedProduct =  productRepository.save(newProduct);

            //Guardamos imagen
            imageService.uploadImage(productRequest.getImage());

            return savedProduct;

        }
        throw new ProductDuplicateException();
    }

    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public Product updateProductAttribute(Long productId, ProductRequest updatedAttributes) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        // Actualizar solo los atributos proporcionados en updatedAttributes
        if (updatedAttributes.getName() != null) {
            existingProduct.setName(updatedAttributes.getName());
        }
        if (updatedAttributes.getDescription() != null) {
            existingProduct.setDescription(updatedAttributes.getDescription());
        }
        // Aquí continuarías con los demás atributos...

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId) throws ProductNotFoundException {
        // Verificar si el producto existe
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        // Si el producto existe, eliminarlo
        productRepository.deleteById(productId);
    }

    protected void reduceQuantity(Long productId, int quantity){
        this.productRepository.reduceQuantity(productId, quantity);
    }

}
