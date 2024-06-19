package com.uade.tpo.demo.service;

import com.uade.tpo.demo.controller.OrderNotFoundException;
import com.uade.tpo.demo.entity.*;
import com.uade.tpo.demo.entity.dto.OrderDTO;
import com.uade.tpo.demo.exceptions.OrderNotPossibleException;
import com.uade.tpo.demo.repository.OrderRepository;
import com.uade.tpo.demo.repository.ProductOrderedRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private CodigosService codigosService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductOrderedRepository productOrderedRepository;

    @Transactional
    public Order registrarOrden(OrderRequest datosOrden, User user) throws OrderNotPossibleException {

        //Creamos el hashmap de productos y verificamos disponibilidad
        HashMap<Product,Integer> products = crearHashmapProductos(datosOrden.getProducts());

        //Creamos la lista de codigos y reducimos su disponibilidad
        List<CodigoDescuento> codigoDescuentos = limpiarCodigos(datosOrden.getCodigos());
        for (CodigoDescuento codigoDescuento : codigoDescuentos){
            codigosService.reduceByOne(codigoDescuento.getCodigo());
        }

        //Calculamos el total
        float total = calcularTotal(products, codigoDescuentos);

        //Si no hay error de disponibilidad en ningun producto, modificamos el stock.
        for (Map.Entry<Long, Integer> entry : datosOrden.getProducts().entrySet()) {
            productService.reduceQuantity(entry.getKey(),entry.getValue());
        }

        //Creamos y persistimos la orden
        Order newOrder = Order.builder()
                .user(user)
                .timeOfPurchase(LocalDateTime.now())
                .total(total)
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

    public OrderDTO getbyId(Long orderID) throws OrderNotFoundException {
        Optional<Order> orderBuscada = orderRepository.findById(orderID);
        if (orderBuscada.isPresent()) {
            Order order = orderBuscada.get();
            List<ProductOrdered> productsOrdered = productOrderedRepository.findAllByOrderId(orderID);

            return new OrderDTO(order, productsOrdered);
        } else {
            throw new OrderNotFoundException();
        }
    }

    private float calcularTotal(HashMap<Product, Integer> products, List<CodigoDescuento> codigos) {
        float totalCalculado = 0;
        for (Product producto : products.keySet()) {
            totalCalculado += producto.getPrice() * products.get(producto);
        }

        float totalDescuento = 0;
        for (CodigoDescuento codigo : codigos) {
            if (codigo.getDisponibles() > 0){
                totalDescuento += totalCalculado * codigo.getDescuento();
            }
        }

        totalCalculado -= totalDescuento;

        // Asegurar que el total no sea menor a 0
        if (totalCalculado < 0) {
            totalCalculado = 0;
        }

        return totalCalculado;
    }

    private HashMap<Product,Integer> crearHashmapProductos(HashMap<Long, Integer> datos) throws OrderNotPossibleException {
        HashMap<Product,Integer> products = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : datos.entrySet()) {
            Product producto = productService.getProductById(entry.getKey());
            if(producto.getStock() >= entry.getValue()){
                products.put(producto,entry.getValue());
            } else {
                throw new OrderNotPossibleException();
            }
        }
        return products;
    }

    public float getPreTotal(ConsultaOrden consultaOrden) throws OrderNotPossibleException {
        HashMap<Product,Integer> productosObtenidos = crearHashmapProductos(consultaOrden.getProductos());
        List<CodigoDescuento> codigoDescuentos = limpiarCodigos(consultaOrden.getCodigos());
        return calcularTotal(productosObtenidos, codigoDescuentos);
    }

    private List<CodigoDescuento> limpiarCodigos(List<String> codigos){
        List<CodigoDescuento> codigosLimpios = new ArrayList<>();
        for (String codigo : codigos) {
            Optional<CodigoDescuento> codigoBuscado = codigosService.getDescuento(codigo.toUpperCase());
            if (codigoBuscado.isPresent() && !codigosLimpios.contains(codigoBuscado) && codigoBuscado.get().getDisponibles() > 0) {
                codigosLimpios.add(codigoBuscado.get());
            }
        }
        return codigosLimpios;
    }

}
