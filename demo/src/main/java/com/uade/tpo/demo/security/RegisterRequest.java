package com.uade.tpo.demo.security;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String lastName;
    private boolean administrator;
    private String email;
    private String password;
}
