package com.uade.tpo.demo.service;

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

    public List<Product> buscarTodosProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequest productRequest) throws ProductDuplicateException, IOException {
        List<Product> products = productRepository.findByName(productRequest.getName());
        if (products.isEmpty()) {
            Product newProduct = new Product();
            newProduct.setName(productRequest.getName());
            newProduct.setDescription(productRequest.getDescription());
            newProduct.setPrice(productRequest.getPrice());
            newProduct.setCategory(productRequest.getCategory());
            newProduct.setStock(productRequest.getStock());

            // Guardar el archivo de imagen
            MultipartFile imageFile = productRequest.getImageUrl();
            String fileName = imageFile.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
            Path filePath = Paths.get("product-images", newFileName);
            Files.createDirectories(filePath.getParent());
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Establecer la URL de acceso a la imagen
            String imageUrl = "/product-images/" + newFileName;
            newProduct.setImageUrl(imageUrl);

            return productRepository.save(newProduct);
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
}
