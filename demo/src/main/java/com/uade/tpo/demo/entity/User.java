package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
<<<<<<< Updated upstream
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
>>>>>>> Stashed changes
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "USER_USERNAME", nullable = false)
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
