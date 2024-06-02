package com.uade.tpo.demo.exceptions;

public class ProductDuplicateException extends RuntimeException {

    public ProductDuplicateException() {
        super();
    }

    public ProductDuplicateException(String message) {
        super(message);
    }

    public ProductDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
