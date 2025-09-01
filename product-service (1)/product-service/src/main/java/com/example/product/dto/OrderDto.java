package com.example.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDto {

    @JsonProperty("orderid")
    private Long _OrderId;

    @JsonProperty("productid")
    private Long _ProductId;

    @JsonProperty("username")
    private String _UserName;

    @JsonProperty("quantity")
    private int _Quantity;

    @JsonProperty("totalprice")
    private BigDecimal _TotalPrice;

    @JsonProperty("status")
    private String _Status; // e.g., PLACED, SHIPPED, CANCELED

    @JsonProperty("orderdate")
    private LocalDateTime _OrderDate;

    @JsonProperty("message")
    private String _Message;

    // Default constructor
    public OrderDto() {
    }

    // Parameterized constructor
    public OrderDto(Long orderId, Long productId, String userName, int quantity,
                    BigDecimal totalPrice, String status, LocalDateTime orderDate, String message) {
        this._OrderId = orderId;
        this._ProductId = productId;
        this._UserName = userName;
        this._Quantity = quantity;
        this._TotalPrice = totalPrice;
        this._Status = status;
        this._OrderDate = orderDate;
        this._Message = message;
    }

    // Getters and Setters
    public Long getOrderId() {
        return _OrderId;
    }

    public void setOrderId(Long orderId) {
        this._OrderId = orderId;
    }

    public Long getProductId() {
        return _ProductId;
    }

    public void setProductId(Long productId) {
        this._ProductId = productId;
    }

    public String getUserName() {
        return _UserName;
    }

    public void setUserName(String userName) {
        this._UserName = userName;
    }

    public int getQuantity() {
        return _Quantity;
    }

    public void setQuantity(int quantity) {
        this._Quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return _TotalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this._TotalPrice = totalPrice;
    }

    public String getStatus() {
        return _Status;
    }

    public void setStatus(String status) {
        this._Status = status;
    }

    public LocalDateTime getOrderDate() {
        return _OrderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this._OrderDate = orderDate;
    }

    public String getMessage() {
        return _Message;
    }

    public void setMessage(String message) {
        this._Message = message;
    }
}
