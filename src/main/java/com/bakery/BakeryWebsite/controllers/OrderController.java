package com.bakery.BakeryWebsite.controllers;

import com.bakery.BakeryWebsite.models.Order;
import com.bakery.BakeryWebsite.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController() {
        this.orderService = new OrderService();
    }

    @GetMapping("/")
    public String showOrderForm() {
        return "redirect:/order-form.html";
    }

    @PostMapping("/add-order")
    public String addOrder(
            @RequestParam String customerName,
            @RequestParam String cakeType,
            @RequestParam String pickupTime,
            @RequestParam String orderType) throws IOException {
        String orderId = UUID.randomUUID().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime parsedPickupTime = LocalDateTime.parse(pickupTime, formatter);

        Order order = new Order(orderId, customerName, cakeType, parsedPickupTime, Order.OrderType.valueOf(orderType));
        orderService.addOrder(order);
        return "redirect:/order-list.html";
    }
}