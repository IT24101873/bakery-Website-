package com.bakery.BakeryWebsite.repository;

import com.bakery.BakeryWebsite.models.Order;
import com.bakery.BakeryWebsite.models.CustomCakeOrder;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static final String FILE_PATH = "C:\\Users\\Savindu\\IdeaProjects\\bakery-Website-\\orders.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void saveOrder(Order order) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line;
            if (order instanceof CustomCakeOrder customCakeOrder) {
                line = order.getOrderId() + "," + order.getCustomerName() + "," + order.getItem() + "," +
                        order.getPickupTime().format(FORMATTER) + ",CustomCake," + customCakeOrder.getCakeDesign() + "," + order.getStatus();
            } else {
                line = order.getOrderId() + "," + order.getCustomerName() + "," + order.getItem() + "," +
                        order.getPickupTime().format(FORMATTER) + ",Regular,," + order.getStatus();
            }
            writer.write(line);
            writer.newLine();
        }
    }

    public List<Order> getAllOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String orderId = parts[0];
                String customerName = parts[1];
                String item = parts[2];
                LocalDateTime pickupTime = LocalDateTime.parse(parts[3], FORMATTER);
                String type = parts[4];
                String cakeDesign = parts.length > 5 ? parts[5] : "";
                String status = parts.length > 6 ? parts[6] : "PENDING";
                Order order;
                if ("CustomCake".equals(type)) {
                    order = new CustomCakeOrder(orderId, customerName, item, pickupTime, cakeDesign);
                } else {
                    order = new Order(orderId, customerName, item, pickupTime);
                }
                order.setStatus(Order.Status.valueOf(status));
                orders.add(order);
            }
        }
        return orders;
    }

    public void updateOrder(Order updatedOrder) throws IOException {
        List<Order> orders = getAllOrders();
        List<Order> updatedOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getOrderId().equals(updatedOrder.getOrderId())) {
                updatedOrders.add(updatedOrder);
            } else {
                updatedOrders.add(order);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Order order : updatedOrders) {
                String line;
                if (order instanceof CustomCakeOrder customCakeOrder) {
                    line = order.getOrderId() + "," + order.getCustomerName() + "," + order.getItem() + "," +
                            order.getPickupTime().format(FORMATTER) + ",CustomCake," + customCakeOrder.getCakeDesign() + "," + order.getStatus();
                } else {
                    line = order.getOrderId() + "," + order.getCustomerName() + "," + order.getItem() + "," +
                            order.getPickupTime().format(FORMATTER) + ",Regular,," + order.getStatus();
                }
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public void deleteOrder(String orderId) throws IOException {
        List<Order> orders = getAllOrders();
        orders.removeIf(order -> order.getOrderId().equals(orderId));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Order order : orders) {
                String line;
                if (order instanceof CustomCakeOrder customCakeOrder) {
                    line = order.getOrderId() + "," + order.getCustomerName() + "," + order.getItem() + "," +
                            order.getPickupTime().format(FORMATTER) + ",CustomCake," + customCakeOrder.getCakeDesign() + "," + order.getStatus();
                } else {
                    line = order.getOrderId() + "," + order.getCustomerName() + "," + order.getItem() + "," +
                            order.getPickupTime().format(FORMATTER) + ",Regular,," + order.getStatus();
                }
                writer.write(line);
                writer.newLine();
            }
        }
    }
}