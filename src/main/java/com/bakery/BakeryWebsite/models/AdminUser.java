package com.bakery.BakeryWebsite.models;


public class AdminUser extends User {

    public AdminUser() {
        super();
        setRole("ADMIN");
    }

    public AdminUser(int id, String username, String password) {
        super(id, username, password, "ADMIN");
    }
}

