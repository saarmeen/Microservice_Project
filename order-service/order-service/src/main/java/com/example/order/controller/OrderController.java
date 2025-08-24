package com.example.order.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public String getAllOrders() {
        return "All orders endpoint working!";
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable Long id) {
        return "Order details for ID " + id;
    }

    @PostMapping
    public String placeOrder() {
        return "Place order endpoint working!";
    }

    @PutMapping("/{id}")
    public String updateOrder(@PathVariable Long id) {
        return "Update order endpoint working for ID " + id;
    }

    @DeleteMapping("/{id}")
    public String cancelOrder(@PathVariable Long id) {
        return "Cancel order endpoint working for ID " + id;
    }
}
