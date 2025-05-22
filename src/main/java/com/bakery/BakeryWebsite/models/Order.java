package com.bakery.BakeryWebsite.models;

import java.time.LocalDateTime;

public class Order {
    private String orderId;
    private String customerName;
    private String cakeType;
    private LocalDateTime pickupTime;
    private Status status;
    private OrderType orderType;

    public enum Status {
        PENDING, READY, CANCELED
    }

    public enum OrderType {
        CUSTOM, STANDARD
    }

    public Order(String orderId, String customerName, String cakeType, LocalDateTime pickupTime, OrderType orderType) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.cakeType = cakeType;
        this.pickupTime = pickupTime;
        this.status = Status.PENDING;
        this.orderType = orderType;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getCakeType() { return cakeType; }
    public void setCakeType(String cakeType) { this.cakeType = cakeType; }
    public LocalDateTime getPickupTime() { return pickupTime; }
    public void setPickupTime(LocalDateTime pickupTime) { this.pickupTime = pickupTime; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public OrderType getOrderType() { return orderType; }
    public void setOrderType(OrderType orderType) { this.orderType = orderType; }

    public String getOrderDetails() {
        return "Order ID: " + orderId + ", Customer: " + customerName + ", Cake Type: " + cakeType +
                ", Pickup: " + pickupTime + ", Status: " + status + ", Order Type: " + orderType;
    }
}