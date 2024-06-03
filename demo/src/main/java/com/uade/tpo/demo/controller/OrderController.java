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

    @PostMapping("/crearOrden")
    public Order registrarOrden(@RequestBody OrderRequest datosOrden) throws OrderNotPossibleException {
        //Verificamos que exista el usuario
        Optional<User> usuario = Optional.ofNullable(userService.buscarUsuarioUnico(datosOrden.getUserEmail()));
        if (usuario.isPresent()){
            //Generamos Orden
            return orderService.registrarOrden(datosOrden, usuario.get());
        } else {
            throw new OrderNotPossibleException();
        }
    };

}
