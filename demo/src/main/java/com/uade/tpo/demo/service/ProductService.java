package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.ProductRequest;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;
import com.uade.tpo.demo.exceptions.ProductNotFoundException;
import com.uade.tpo.demo.repository.ProductRepository;
<<<<<<< Updated upstream
=======
import com.uade.tpo.demo.util.ImageUtil;
import jakarta.transaction.Transactional;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    public List<Product> buscarTodosProducts() {
=======
    @Autowired
    private ImageService imageService;

    public List<Product> getAll() {
>>>>>>> Stashed changes
        return productRepository.findAll();
    }

    @Transactional
    public Product createProduct(ProductRequest productRequest) throws ProductDuplicateException, IOException {
        //Verificamos si ya hay un product con ese nombre
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
        Optional<Product> productBuscado = productRepository.findById(id);

        if (productBuscado.isPresent()){
            return productBuscado.get();
        } else {
            throw new ProductNotFoundException();
        }
    }
    public List<Product> getProductByCategory(String categoryName) {
        List<Product> products = productRepository.findAllByCategory(categoryName);
        if (products.isEmpty()){
            throw new ProductNotFoundException();
        }
        return products;
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

}
