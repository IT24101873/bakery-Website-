package com.bakery.BakeryWebsite.models;

public class Admin {
    private Long id;
    private String username;
    private String email;

    // Default constructor
    public Admin() {}

    // Parameterized constructor
    public Admin(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ToString for file storage
    @Override
    public String toString() {
        return id + "," + username + "," + email;
    }

    // Parse from string (for reading from file)
    public static Admin fromString(String line) {
        String[] parts = line.split(",");
        return new Admin(Long.parseLong(parts[0]), parts[1], parts[2]);
    }
}