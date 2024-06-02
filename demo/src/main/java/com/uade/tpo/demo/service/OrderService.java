package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.OrderRequest;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    public Order registrarOrden(OrderRequest datosOrden, User user) {
        //Creamos el hasmap de productos
        HashMap<Product,Integer> products = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : datosOrden.getProducts().entrySet()) {
            Long key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Clave: " + key + ", Valor: " + value);
        }

        //Creamos la Orden
        Order order = new Order(
                user.getId(),
                datosOrden.getTimeOfPurchase(),
                datosOrden.getTotal(),
                datosOrden.getCardNumber(),
                datosOrden.getAddress(),

        );

        //Creamos los ProductOrder

        //Persistimos

    }
}
