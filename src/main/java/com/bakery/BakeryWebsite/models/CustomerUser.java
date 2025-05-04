package com.bakery.BakeryWebsite.models;

public class CustomerUser extends User {

    public CustomerUser() {
        super();
        setRole("CUSTOMER");
    }

    public CustomerUser(int id, String username, String password) {
        super(id, username, password, "CUSTOMER");
    }
}
