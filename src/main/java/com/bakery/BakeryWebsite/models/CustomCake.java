package com.bakery.BakeryWebsite.models;


public class CustomCake extends Product {
    private String customMessage;


    public CustomCake() {}

    public CustomCake(Long id, String name, double price, String customMessage) {
        super(id, name, price);
        this.customMessage = customMessage;
    }

    // Getter and Setter
    public String getCustomMessage() { return customMessage; }
    public void setCustomMessage(String customMessage) { this.customMessage = customMessage; }
}


