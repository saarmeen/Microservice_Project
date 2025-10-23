package com.example.order.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.order.iRepository.iOrderRepository;
import com.example.order.model.Order;

@Repository
public class OrderRepository implements iOrderRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM orders";
        try {
            return jdbcTemplate.query(sql, new OrderRowMapper());
        } catch (Exception e) {
            System.out.println("Error fetching orders: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Order getOrderById(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new OrderRowMapper(), id);
        } catch (Exception e) {
            System.out.println("Error fetching order for id " + id + " : " + e.getMessage());
            return null;
        }
    }

    @Override
    public Order addOrder(Order order) {
        try {
            String sql = "INSERT INTO orders (product_id, user_name, quantity, total_price, status, order_date) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    order.getProductId(),
                    order.getUserName(),
                    order.getQuantity(),
                    order.getTotalPrice(),
                    order.getStatus(),
                    order.getOrderDate()
            );

            // fetch back last inserted id
            Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
            order.setId(id);

            return order;
        } catch (Exception e) {
            System.out.println("Error adding order: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Order updateOrder(Order order) {
        String sql = "UPDATE orders SET product_id = ?, user_name = ?, quantity = ?, total_price = ?, status = ?, order_date = ? WHERE id = ?";
        try {
            int updated = jdbcTemplate.update(sql,
                    order.getProductId(),
                    order.getUserName(),
                    order.getQuantity(),
                    order.getTotalPrice(),
                    order.getStatus(),
                    order.getOrderDate(),
                    order.getId()
            );

            return updated > 0 ? order : null;
        } catch (Exception e) {
            System.out.println("Error updating order: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteOrder(Long id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try {
            int deleted = jdbcTemplate.update(sql, id);
            return deleted > 0;
        } catch (Exception e) {
            System.out.println("Error deleting order with id " + id + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getOrderStatusById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Order> getOrdersPaginated(int offset, int limit) {
    String sql = "SELECT * FROM orders LIMIT ? OFFSET ?";
    try {
        // Pass parameters in the same order as in SQL: LIMIT, OFFSET
        return jdbcTemplate.query(sql, new OrderRowMapper(), limit, offset);
    } catch (Exception e) {
        System.err.println("Error fetching paginated orders: " + e.getMessage());
        return new ArrayList<>(); // Return empty list instead of null
    }
}


    @Override
    public int countOrders() {
        String sql = "SELECT COUNT(*) FROM orders";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception e) {
            System.out.println("Error counting orders: " + e.getMessage());
            return 0;
        }
    }

}
