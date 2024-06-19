package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.CodigoDescuento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoRepository extends JpaRepository<CodigoDescuento, String> {

    @Modifying
    @Transactional
    @Query("update CodigoDescuento c set c.disponibles = c.disponibles - 1 where c.codigo = :codigo")
    void reduceQuantityByOne(String codigo);
}
