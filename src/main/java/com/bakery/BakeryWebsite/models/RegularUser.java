package com.bakery.BakeryWebsite.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class RegularUser extends User {
    public RegularUser() {
        setRole("CUSTOMER");
    }

    public RegularUser(int id, String username, String password, String email, String role) {
        super(id, username, password,email, "CUSTOMER");
    }

    @Override
    public String getUserType() {
        return "Customer";
    }
}
