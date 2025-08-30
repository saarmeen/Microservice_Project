package com.example.order.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.example.order.model.Order;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (rs == null) {
            return null;
        }

        Order order = new Order();

        order.setId(rs.getLong("id"));
        order.setProductId(rs.getLong("product_id"));
        order.setUserName(rs.getString("user_name"));
        order.setQuantity(rs.getInt("quantity"));
        order.setTotalPrice(rs.getBigDecimal("total_price"));
        order.setStatus(rs.getString("status"));

        Timestamp ts = rs.getTimestamp("order_date");
        if (ts != null) {
            order.setOrderDate(ts.toLocalDateTime()); // DATETIME → LocalDateTime
        }

        return order;
    }
}
