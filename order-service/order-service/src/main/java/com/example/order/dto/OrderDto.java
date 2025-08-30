package com.example.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDto {

    private Long id;
    private Long productId;
    private String userName;
    private int quantity;
    private BigDecimal totalPrice;
    private String status; // e.g., PLACED, SHIPPED, CANCELED
    private LocalDateTime orderDate;
    private String message; // ✅ Added message field with getter/setter

    public OrderDto() {
    }

    public OrderDto(Long id, Long productId, String userName, int quantity, BigDecimal totalPrice, String status, LocalDateTime orderDate, String message) {
        this.id = id;
        this.productId = productId;
        this.userName = userName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDate = orderDate;
        this.message = message;
    }

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

    public void setStatus(String status) { // ✅ Fixed parameter name
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getMessage() {  // ✅ Getter
        return message;
    }

    public void setMessage(String message) {  // ✅ Setter
        this.message = message;
    }
}
