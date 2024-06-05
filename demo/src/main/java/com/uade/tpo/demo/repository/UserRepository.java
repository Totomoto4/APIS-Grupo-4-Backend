package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.User;

import org.springframework.stereotype.Repository;

import java.util.Optional;

<<<<<<< Updated upstream
=======
import org.springframework.data.jpa.repository.JpaRepository;

>>>>>>> Stashed changes
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
