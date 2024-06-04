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
    private UserService userService;

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

        //Creamos y persistimos la orden
        Order newOrder = Order.builder()
                .user(user)
                .timeOfPurchase(datosOrden.getTimeOfPurchase())
                .total(datosOrden.getTotal())
                .cardNumber(datosOrden.getCardNumber())
                .address(datosOrden.getAddress())
                .products(products)
                .build();
        orderRepository.save(newOrder);
        long orderId = newOrder.getId();
        System.out.println("Order ID: " + newOrder.getId());

        //Creamos los ProductOrder
        List<ProductOrdered> productosPedidos = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            ProductOrdered productoPedido = ProductOrdered.builder()
                    .order(newOrder)
                    .name(entry.getKey().getName())
                    .price(entry.getKey().getPrice())
                    .quantity(entry.getValue())
                    .build();
            productosPedidos.add(productoPedido);
        }

        //Persistimos
        System.out.println("Orden registrada!");
        for (ProductOrdered productoPedido: productosPedidos){
            productOrderedRepository.save(productoPedido);
            System.out.println(productoPedido);
        }

        return newOrder;
    }
}
