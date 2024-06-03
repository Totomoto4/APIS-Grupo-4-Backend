package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.ProductOrdered;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductOrderedRepository {

    @Autowired
    EntityManager entityManager;

    public void registrarProductOrdered(ProductOrdered productOrdered){
        this.entityManager.persist(productOrdered);
    }
}
