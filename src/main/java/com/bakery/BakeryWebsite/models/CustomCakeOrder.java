package com.bakery.BakeryWebsite.models;

import java.time.LocalDateTime;

public class CustomCakeOrder extends Order {
    private String cakeDesign;

    public CustomCakeOrder(String orderId, String customerName, String item, LocalDateTime pickupTime, String cakeDesign) {
        super(orderId, customerName, item, pickupTime);
        this.cakeDesign = cakeDesign;
    }

    public String getCakeDesign() { return cakeDesign; }
    public void setCakeDesign(String cakeDesign) { this.cakeDesign = cakeDesign; }

    @Override
    public String getOrderDetails() {
        return super.getOrderDetails() + ", Cake Design: " + cakeDesign;
    }
}