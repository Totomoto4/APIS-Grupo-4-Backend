package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);

    Optional<Image> findByProductId(Long productId);

    void deleteByProductId(Long productId);
}
