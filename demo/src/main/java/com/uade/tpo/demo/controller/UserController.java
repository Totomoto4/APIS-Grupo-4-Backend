package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.UserAccess;
import com.uade.tpo.demo.entity.UserDTO;
import com.uade.tpo.demo.exceptions.UserEmailDuplicateException;
import com.uade.tpo.demo.exceptions.UserNotFoundException;
import com.uade.tpo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/loginUser")
    public ResponseEntity<Object> checkUser(@RequestBody UserAccess userAccess) throws UserNotFoundException {
        UserDTO result = userService.checkUser(userAccess);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @PostMapping("/createUser")
    public ResponseEntity<Object> crearUser(@RequestBody User userCreado) throws UserEmailDuplicateException {
        UserDTO result = userService.crearUser(userCreado);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

