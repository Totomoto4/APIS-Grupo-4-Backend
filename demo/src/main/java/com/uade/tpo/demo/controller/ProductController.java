package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping()
    public List<Product> buscarTodosProducts(){
        return productService.buscarTodosProducts();
    }
}
