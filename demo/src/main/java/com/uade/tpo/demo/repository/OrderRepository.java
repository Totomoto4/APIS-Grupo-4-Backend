package com.uade.tpo.demo.repository;

import com.uade.tpo.demo.entity.Order;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
