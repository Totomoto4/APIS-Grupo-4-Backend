package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> buscarTodos() {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User ", User.class);

        return theQuery.getResultList();
    }

    public User buscarUnico(String username) {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User WHERE username = :username", User.class);
        theQuery.setParameter("username", username);
        return theQuery.getSingleResult();
    }

}
