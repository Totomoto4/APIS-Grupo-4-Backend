package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Image;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.ProductRequest;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;
import com.uade.tpo.demo.exceptions.ProductNotFoundException;
import com.uade.tpo.demo.repository.ProductRepository;
import com.uade.tpo.demo.util.ImageUtil;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Product createProduct(ProductRequest productRequest) throws ProductDuplicateException, IOException {
        List<Product> products = productRepository.findByName(productRequest.getName());
        if (products.isEmpty()) {
            //Creamos producto
            Product newProduct = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .category(productRequest.getCategory())
                    .stock(productRequest.getStock())
                    .build();
            //Guardamos producto
            productRepository.save(newProduct);

            //Guardamos imagen
            Image newImage = Image.builder()
                    .product(newProduct)
                    .name(productRequest.getImage().getOriginalFilename())
                    .type(productRequest.getImage().getContentType())
                    .imageData(ImageUtil.compressImage(productRequest.getImage().getBytes())).build();

            imageService.uploadImage(newImage);

            return newProduct;
        }
        throw new ProductDuplicateException();
    }

    public Product getProductById(Long id) {
        Optional<Product> productBuscado = productRepository.findById(id);

        if (productBuscado.isPresent()){
            return productBuscado.get();
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Transactional
    public Product updateProductAttribute(Long productId, ProductRequest updatedAttributes) throws ProductNotFoundException, IOException {

        Optional<Product> productToUpdate = productRepository.findById(productId);
        if (productToUpdate.isPresent()){

            // Actualizar solo los atributos proporcionados en updatedAttributes
            if (updatedAttributes.getName() != null && !updatedAttributes.getName().equals("#null#")) {
                productToUpdate.get().setName(updatedAttributes.getName());
            }
            if (updatedAttributes.getDescription() != null && !updatedAttributes.getDescription().equals("#null#")) {
                productToUpdate.get().setDescription(updatedAttributes.getDescription());
            }
            if (updatedAttributes.getPrice() != null) {
                productToUpdate.get().setPrice(updatedAttributes.getPrice());
            }
            if (updatedAttributes.getCategory() != null && !updatedAttributes.getCategory().equals("#null#")) {
                productToUpdate.get().setCategory(updatedAttributes.getCategory());
            }
            if (updatedAttributes.getStock() != null) {
                productToUpdate.get().setStock(updatedAttributes.getStock());
            }

            //Si cambia la imagen
            if (updatedAttributes.getImage() != null){
                imageService.updateImage(productToUpdate.get().getId(), updatedAttributes.getImage());
            }

            return productRepository.save(productToUpdate.get());

        } else {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
    }

    @Transactional
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            imageService.deleteImage(productId);
            productRepository.delete(product.get());
        } else {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }
    }

    protected void reduceQuantity(Long productId, int quantity){
        this.productRepository.reduceQuantity(productId, quantity);
    }

    public List<Product> getProductByCategory(String categoryName) {
        List<Product> products = productRepository.findAllByCategory(categoryName);
        if (products.isEmpty()){
            throw new ProductNotFoundException();
        }
        return products;
    }
}
