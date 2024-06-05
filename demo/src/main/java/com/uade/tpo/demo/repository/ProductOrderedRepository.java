package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.ProductOrdered;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
<<<<<<< Updated upstream
public class ProductOrderedRepository {

    private final EntityManager entityManager;

    @Autowired
    public ProductOrderedRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void registrarProductOrdered(ProductOrdered productOrdered){
        this.entityManager.persist(productOrdered);
    }
=======
public interface ProductOrderedRepository extends JpaRepository<ProductOrdered, Long> {

    List<ProductOrdered> findAllByOrderId(Long orderID);
>>>>>>> Stashed changes
}
