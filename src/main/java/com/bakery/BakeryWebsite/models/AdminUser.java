package com.bakery.BakeryWebsite.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
@JsonIgnoreProperties(ignoreUnknown = true)

@Setter
@Getter
public class AdminUser extends User {
    // Getter and Setter for adminLevel
    private String adminLevel; // e.g., "SUPER_ADMIN", "MODERATOR"

    public AdminUser() {
        setRole("ADMIN");
    }

    public AdminUser(int id, String username, String password, String email, String adminLevel) {
        super(id, username, password, email, "ADMIN");
        this.adminLevel = adminLevel;
    }


    @Override
    public String getUserType() {
        return "Admin";
    }

}
