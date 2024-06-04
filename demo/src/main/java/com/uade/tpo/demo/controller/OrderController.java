package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.OrderRequest;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.exceptions.OrderNotPossibleException;
import com.uade.tpo.demo.service.OrderService;
import com.uade.tpo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping()
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/createOrder")
    public Order registrarOrden(@RequestBody OrderRequest datosOrden) throws OrderNotPossibleException {
        //Verificamos que exista el usuario
        Optional<User> usuario = userService.buscarUsuarioUnico(datosOrden.getUserEmail());
        if (usuario.isPresent()){
            //Generamos Orden
            System.out.println("Usuario encontrado");
            return orderService.registrarOrden(datosOrden, usuario.get());
        } else {
            System.out.println("No se encontro el user");
            throw new OrderNotPossibleException();
        }
    };

}