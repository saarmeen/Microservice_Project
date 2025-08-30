package com.example.order.iRepository;

import java.util.List;
import com.example.order.model.Order;

public interface iOrderRepository {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    String getOrderStatusById(Long id);
    Order addOrder(Order order);
    Order updateOrder(Order order);
    boolean deleteOrder(Long id);
}
