package com.example.product.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.product.dto.OrderDto;

public class Product {

    private Long id;                
    private String name;            
    private String description;     
    private BigDecimal price;       
    private int stock;              
    private LocalDateTime createdAt; 
    private OrderDto orders;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public OrderDto getOrders() {
        return orders;
    }

    public void setOrders(OrderDto orders) {
        this.orders = orders;
    }
}
