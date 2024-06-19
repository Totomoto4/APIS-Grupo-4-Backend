package com.uade.tpo.demo.security;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;

    private String password;
}
