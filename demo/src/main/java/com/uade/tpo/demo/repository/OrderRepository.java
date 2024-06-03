package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.Order;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    @Autowired
    EntityManager entityManager;

    public void registrarOrden(Order order){
        this.entityManager.persist(order);
    }
}
