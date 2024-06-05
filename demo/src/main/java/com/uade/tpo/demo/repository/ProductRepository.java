package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p")
    List<Product> findAll();

    @Query("select p from Product p where p.name = ?1")
    List<Product> findByName(String name);

    @Modifying
    @Transactional
    @Query("update Product p set p.stock = p.stock - :quantity where p.id = :productId and p.stock >= :quantity")
    void reduceQuantity(Long productId, int quantity);

    List<Product> findAllByCategory(String categoryName);
}