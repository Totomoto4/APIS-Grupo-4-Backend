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

    @Autowired
    private ProductOrderedRepository productOrderedRepository;

    public Order registrarOrden(OrderRequest datosOrden, User user) {
        //Creamos el hashmap de productos y verificamos disponibilidad
        HashMap<Product,Integer> products = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : datosOrden.getProducts().entrySet()) {
            Product producto = productService.getProductById(entry.getKey());
            if(producto.getStock() >= entry.getValue()){
                products.put(producto,entry.getValue());
            } else {
                throw new RuntimeException();
            }

        }

        //Si no hay error de disponibilidad en ningun producto, modificamos el stock.
        for (Map.Entry<Long, Integer> entry : datosOrden.getProducts().entrySet()) {
            productService.reduceQuantity(entry.getKey(),entry.getValue());
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

        //Persistimos Orden
        orderRepository.registrarOrden(order);
        long orderId = order.getId();
        System.out.println("Order ID: " + orderId);

        //Creamos los ProductOrder
        List<ProductOrdered> productosPedidos = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            ProductOrdered productoPedido = new ProductOrdered(
                    orderId,
                    entry.getKey().getName(),
                    entry.getKey().getPrice(),
                    entry.getValue());
            productosPedidos.add(productoPedido);
        }

        //Persistimos
        System.out.println("Orden registrada!");
        for (ProductOrdered productoPedido: productosPedidos){
            productOrderedRepository.registrarProductOrdered(productoPedido);
            System.out.println(productoPedido);
        }

        return order;
    }
}
