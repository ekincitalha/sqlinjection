package com.example.sqlinjection.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

}
