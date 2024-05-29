package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    private final EntityManager entityManager;

    @Autowired
    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> buscarTodos() {
        TypedQuery<Product> theQuery = entityManager.createQuery("FROM Product ", Product.class);

        return theQuery.getResultList();
    }
}
