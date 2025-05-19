package com.bakery.BakeryWebsite.services;

import com.bakery.BakeryWebsite.models.Order;
import com.bakery.BakeryWebsite.repository.OrderRepository;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrderService {
    private final OrderRepository orderRepository;
    private final Queue<Order> orderQueue;

    public OrderService() {
        this.orderRepository = new OrderRepository();
        this.orderQueue = new LinkedList<>();
    }

    public void addOrder(Order order) throws IOException {
        orderQueue.add(order);
        orderRepository.saveOrder(order);
    }

    public Order processNextOrder() {
        return orderQueue.poll();
    }

    public List<Order> getAllOrders() throws IOException {
        return orderRepository.getAllOrders();
    }

    public void updateOrder(Order updatedOrder) throws IOException {
        orderRepository.updateOrder(updatedOrder);
        Queue<Order> tempQueue = new LinkedList<>();
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll();
            if (order.getOrderId().equals(updatedOrder.getOrderId())) {
                tempQueue.add(updatedOrder);
            } else {
                tempQueue.add(order);
            }
        }
        orderQueue.addAll(tempQueue);
    }

    public void deleteOrder(String orderId) throws IOException {
        orderRepository.deleteOrder(orderId);
        orderQueue.removeIf(order -> order.getOrderId().equals(orderId));
    }

    public List<Order> sortOrdersByPickupTime(List<Order> orders) {
        int n = orders.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders.get(j).getPickupTime().isAfter(orders.get(j + 1).getPickupTime())) {
                    Order temp = orders.get(j);
                    orders.set(j, orders.get(j + 1));
                    orders.set(j + 1, temp);
                }
            }
        }
        return orders;
    }
}
