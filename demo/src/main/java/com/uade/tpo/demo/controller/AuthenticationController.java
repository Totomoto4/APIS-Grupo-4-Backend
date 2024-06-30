package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.exceptions.UserEmailDuplicateException;
import com.uade.tpo.demo.exceptions.UserNotFoundException;
import com.uade.tpo.demo.security.AuthenticationRequest;
import com.uade.tpo.demo.security.AuthenticationResponse;
import com.uade.tpo.demo.security.RegisterRequest;
import com.uade.tpo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    /*
    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws UserNotFoundException {
        AuthenticationResponse result = userService.authenticate(request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws UserNotFoundException {
        AuthenticationResponse result = userService.authenticate(request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws UserEmailDuplicateException {
        AuthenticationResponse result = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

