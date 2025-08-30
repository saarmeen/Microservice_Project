package com.example.order.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private Long id;                  // Unique order ID
    private Long productId;           // Reference to the product
    private String userName;          // Name of the user who placed the order
    private int quantity;             // Number of items ordered
    private BigDecimal totalPrice;    // Total price of the order
    private String status;            // e.g. "PLACED", "SHIPPED", "CANCELLED"
    private LocalDateTime orderDate;  // When the order was placed

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
