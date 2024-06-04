package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.ProductOrdered;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductOrderedRepository extends JpaRepository<ProductOrdered, Long> {
}
