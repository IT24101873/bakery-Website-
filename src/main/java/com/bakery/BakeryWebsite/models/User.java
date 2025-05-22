package com.bakery.BakeryWebsite.models;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class User {
    // Getters and Setters
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;


    public User() {}

    public User(int id, String username, String password, String email , String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;

    }

    // Abstract method for polymorphism
    public abstract String getUserType();

}
