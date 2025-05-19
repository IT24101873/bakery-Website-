package com.bakery.BakeryWebsite.models;

import java.time.LocalDateTime;

public class Order {
    private String orderId;
    private String customerName;
    private String item;
    private LocalDateTime pickupTime;
    private Status status;

    public enum Status {
        PENDING, READY, CANCELED
    }

    public Order(String orderId, String customerName, String item, LocalDateTime pickupTime) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.item = item;
        this.pickupTime = pickupTime;
        this.status = Status.PENDING; // Default status
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }
    public LocalDateTime getPickupTime() { return pickupTime; }
    public void setPickupTime(LocalDateTime pickupTime) { this.pickupTime = pickupTime; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getOrderDetails() {
        return "Order ID: " + orderId + ", Customer: " + customerName + ", Item: " + item + ", Pickup: " + pickupTime + ", Status: " + status;
    }
}