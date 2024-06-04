package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.UserDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<User> buscarUnico(String userEmail) {
        try{
            TypedQuery<User> theQuery = entityManager.createQuery("FROM User WHERE email = :userEmail", User.class);
            theQuery.setParameter("userEmail", userEmail);
            return Optional.ofNullable(theQuery.getSingleResult());
        } catch (NoResultException e){
            return Optional.empty();
        }
    }

    public void crearUser(User userCreado) {
        entityManager.persist(userCreado);
    }
}
