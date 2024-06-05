package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.OrderDTO;
import com.uade.tpo.demo.entity.OrderRequest;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.exceptions.OrderNotFoundException;
import com.uade.tpo.demo.exceptions.OrderNotPossibleException;
import com.uade.tpo.demo.service.OrderService;
import com.uade.tpo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Object> registrarOrden(@RequestBody OrderRequest datosOrden) throws OrderNotPossibleException {
        //Verificamos que exista el usuario
        Optional<User> usuario = userService.buscarUsuarioUnico(datosOrden.getUserEmail());

        if (usuario.isPresent()){
            //Generamos Orden
            System.out.println("Usuario encontrado");
            Order generatedOrder = orderService.registrarOrden(datosOrden, usuario.get());
            String result = "Se genero la orden correctamente con el ID: " + generatedOrder.getId();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            System.out.println("No se encontro el user");
            throw new OrderNotPossibleException();
        }
    }

    @GetMapping("/{orderid}")
    public ResponseEntity<Object> getOrderById(@PathVariable("orderid") Long id ) throws OrderNotFoundException {
        OrderDTO result = orderService.getbyId(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
