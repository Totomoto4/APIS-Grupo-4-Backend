package com.uade.tpo.demo.entity;

import lombok.Data;

@Data
public class UserDTO {

    private String username;

    private String name;

    private String lastName;

    //si True -> es ADMIN
    private boolean administrator;

    private String email;

    public UserDTO(User user){
        this.username = user.getUsername();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.administrator = user.isAdministrator();
        this.email = user.getEmail();
    }
}
