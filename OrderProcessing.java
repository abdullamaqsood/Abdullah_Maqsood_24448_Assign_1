package com.mycompany.ecommerce;

import java.util.ArrayList;
import java.util.List;


// The 'OrderProcessing' class adheres to SRP by handling only order-related operations
// such as creating, updating, canceling, and displaying orders. It does not handle 
// payments, inventory, or user authentication, keeping its responsibility focused.

public class OrderProcessing {
    private List<Order> orders;

    public OrderProcessing() {
        this.orders = new ArrayList<>();
    }

    public void createOrder(int orderId, String customerName, List<String> items) {
        Order newOrder = new Order(orderId, customerName, items);
        orders.add(newOrder);
        System.out.println("Order created successfully: " + orderId);
    }

    public void cancelOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            orders.remove(order);
            System.out.println("Order " + orderId + " has been canceled.");
        } else {
            System.out.println("Order not found.");
        }
    }

    public void updateOrder(int orderId, List<String> newItems) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.setItems(newItems);
            System.out.println("Order " + orderId + " has been updated.");
        } else {
            System.out.println("Order not found.");
        }
    }

    private Order findOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}

class Order {
    private int orderId;
    private String customerName;
    private List<String> items;

    public Order(int orderId, String customerName, List<String> items) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = new ArrayList<>(items);
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(List<String> newItems) {
        this.items = new ArrayList<>(newItems);
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + "\nCustomer: " + customerName + "\nItems: " + items;
    }
}
