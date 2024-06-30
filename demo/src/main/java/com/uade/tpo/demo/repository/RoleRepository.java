package com.uade.tpo.demo.repository;


import com.uade.tpo.demo.entity.Role;
import com.uade.tpo.demo.entity.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}