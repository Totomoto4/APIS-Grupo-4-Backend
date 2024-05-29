package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.exceptions.UserEmailDuplicateException;
import com.uade.tpo.demo.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserEmailDuplicateException.class)
    public ResponseEntity<String> handleUserEmailDuplicateException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya hay un usuario con ese email");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un usuario con ese email o contraseña");
    }

    // Manejar otras excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error inesperado");
    }
}