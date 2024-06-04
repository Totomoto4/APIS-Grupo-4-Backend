package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Image;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.ProductRequest;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;
import com.uade.tpo.demo.exceptions.ProductNotFoundException;
import com.uade.tpo.demo.repository.ProductRepository;
import com.uade.tpo.demo.util.ImageUtil;
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
