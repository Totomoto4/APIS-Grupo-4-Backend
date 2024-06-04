package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {


    private final EntityManager entityManager;

    @Autowired
    public OrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void registrarOrden(Order order){
        this.entityManager.persist(order);
    }
}
