package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.entity.*;
import com.uade.tpo.demo.entity.dto.OrderDTO;
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
            String result = "Se generó la orden correctamente con el ID: " + generatedOrder.getId();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            System.out.println("No se encontro el user");
            throw new OrderNotPossibleException();
        }
    }

    @GetMapping()
    public ResponseEntity<Object> verificarTotal(@RequestBody ConsultaOrden consultaOrden) throws OrderNotPossibleException {
        float result = orderService.getPreTotal(consultaOrden);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{orderid}")
    public ResponseEntity<Object> getOrderById(@PathVariable("orderid") Long id ) throws OrderNotFoundException {
        OrderDTO result = orderService.getbyId(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
