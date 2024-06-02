package com.uade.tpo.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "No se puedo registar esta orden. Es posible que el usuario no este registrado.")
public class OrderNotPossibleException extends Exception{
}
