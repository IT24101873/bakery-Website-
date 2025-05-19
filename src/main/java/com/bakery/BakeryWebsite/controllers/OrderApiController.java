package com.bakery.BakeryWebsite.controllers;

import com.bakery.BakeryWebsite.models.Order;
import com.bakery.BakeryWebsite.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class OrderApiController {
    private final OrderService orderService;

    public OrderApiController() {
        this.orderService = new OrderService();
    }

    @GetMapping("/api/orders")
    public List<Order> getOrders() throws IOException {
        List<Order> orders = orderService.getAllOrders();
        return orderService.sortOrdersByPickupTime(orders);
    }

    @GetMapping("/api/admin/orders")
    public List<Order> getAdminOrders() throws IOException {
        List<Order> orders = orderService.getAllOrders();
        return orderService.sortOrdersByPickupTime(orders);
    }

    @PostMapping("/api/admin/update-status")
    public void updateOrderStatus(@RequestParam String orderId, @RequestParam String status) throws IOException {
        List<Order> orders = orderService.getAllOrders();
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setStatus(Order.Status.valueOf(status));
                orderService.updateOrder(order);
                break;
            }
        }
    }

    @DeleteMapping("/api/admin/delete")
    public void deleteOrder(@RequestParam String orderId) throws IOException {
        orderService.deleteOrder(orderId);
    }
}
