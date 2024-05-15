package com.uade.tpo.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("prueba")
    public String prueba(){
        return "Conexion activa";
    }
}
