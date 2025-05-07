package com.bakery.BakeryWebsite.models;

import java.time.LocalDateTime;

public class Feedback {
    protected String id;
    protected String customerName;
    protected LocalDateTime createdAt;

    public Feedback() {
        this.id = java.util.UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    public Feedback(String customerName) {
        this();
        this.customerName = customerName;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

