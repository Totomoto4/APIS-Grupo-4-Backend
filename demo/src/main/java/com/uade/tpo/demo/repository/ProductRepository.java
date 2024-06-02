package com.uade.tpo.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uade.tpo.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p")
    List<Product> findAll();

    @Query("select p from Product p where p.name = ?1")
    List<Product> findByName(String name);
}
