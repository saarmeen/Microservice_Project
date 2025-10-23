package com.example.order.dto;

import java.util.List;

public class PaginatedResponse<T> {
    private int page;
    private int size;
    private int totalPages;
    private int totalElements;
    private List<T> orders;

    // Getters and Setters
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public int getTotalElements() { return totalElements; }
    public void setTotalElements(int totalElements) { this.totalElements = totalElements; }

    public List<T> getOrders() { return orders; }
    public void setOrders(List<T> orders) { this.orders = orders; }
}
