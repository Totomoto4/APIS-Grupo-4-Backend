package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "USER_USERNAME")
    private String username;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_LASTNAME")
    private String lastName;

    //si True -> es ADMIN
    @Column(name = "USER_ADMINISTRATOR")
    private boolean administrator;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "USER_PASSWORD_HASH")
    private String passwordHash;

}
