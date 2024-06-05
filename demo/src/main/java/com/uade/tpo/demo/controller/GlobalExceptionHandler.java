package com.uade.tpo.demo.controller;

import com.uade.tpo.demo.exceptions.*;
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

    @ExceptionHandler(ProductDuplicateException.class)
    public ResponseEntity<String> handleProductDuplicateException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya se encontró un producto con ese nombre");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un producto con ese id");
    }

    @ExceptionHandler(OrderNotPossibleException.class)
    public ResponseEntity<String> handleOrderNotPossibleException() {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se puede procesar la orden. Es posible que no haya stock de algun producto");
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra la orden.");
    }

    @ExceptionHandler(ImageNotAvailableException.class)
    public ResponseEntity<String> handleImageNotAvailableException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró una foto para este id");
    }

    // Manejar otras excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error inesperado");
    }
}