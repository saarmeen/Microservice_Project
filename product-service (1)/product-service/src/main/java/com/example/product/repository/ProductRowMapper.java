package com.example.product.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.example.product.model.Product;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (rs == null) {
            return null;
        }

        Product product = new Product();

        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getBigDecimal("price"));  // maps DECIMAL to BigDecimal
        product.setStock(rs.getInt("stock"));

        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) {
            product.setCreatedAt(ts.toLocalDateTime()); // convert to LocalDateTime
        }

        return product;
    }
}
