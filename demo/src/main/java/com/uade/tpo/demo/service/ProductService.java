package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> buscarTodosProducts() {
        return productRepository.buscarTodos();
    }
}
