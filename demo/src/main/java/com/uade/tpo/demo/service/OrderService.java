package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.*;
import com.uade.tpo.demo.repository.OrderRepository;
import com.uade.tpo.demo.repository.ProductOrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;
    private ProductOrderedRepository productOrderedRepository;

    public Order registrarOrden(OrderRequest datosOrden, User user) {
        //Creamos el hashmap de productos
        HashMap<Product,Integer> products = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : datosOrden.getProducts().entrySet()) {
            Product producto = productService.getProductById(entry.getKey());
            products.put(producto,entry.getValue());
        }

        //Creamos la Orden
        Order order = new Order(
                user.getId(),
                datosOrden.getTimeOfPurchase(),
                datosOrden.getTotal(),
                datosOrden.getCardNumber(),
                datosOrden.getAddress(),
                products
        );

        //Creamos los ProductOrder
        List<ProductOrdered> productosPedidos = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            ProductOrdered productoPedido = new ProductOrdered(
                    order.getId(),
                    entry.getKey().getName(),
                    entry.getKey().getPrice(),
                    entry.getValue());
            productosPedidos.add(productoPedido);
        }

        //Persistimos
        orderRepository.registrarOrden(order);
        for (ProductOrdered productoPedido: productosPedidos){
            productOrderedRepository.registrarProductOrdered(productoPedido);
        }

        return order;
    }
}
