package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.UserDTO;
import com.uade.tpo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> buscarTodosUsers() {return userService.buscarTodosUsuarios();}

    @GetMapping("/login")
    public boolean checkUser(@RequestBody UserDTO userDTO) {
        return userService.checkUser(userDTO);}
}

