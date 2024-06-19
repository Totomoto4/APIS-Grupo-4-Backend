package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.CodigoDescuento;
import com.uade.tpo.demo.repository.CodigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CodigosService {

    @Autowired
    private CodigoRepository codigoRepository;

    public Optional<CodigoDescuento> getDescuento(String codigo){
        return this.codigoRepository.findById(codigo.toUpperCase());
    }

    public void reduceByOne(String codigo){
        this.codigoRepository.reduceQuantityByOne(codigo);
    }

}
