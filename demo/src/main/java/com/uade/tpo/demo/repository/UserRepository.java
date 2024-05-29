package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.UserDTO;
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

    public User buscarUnico(String userEmail) {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User WHERE email = :userEmail", User.class);
        theQuery.setParameter("userEmail", userEmail);
        return theQuery.getSingleResult();
    }

    public void crearUser(User userCreado) {
        entityManager.persist(userCreado);
    }
}
