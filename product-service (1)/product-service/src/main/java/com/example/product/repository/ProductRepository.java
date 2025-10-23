package com.example.product.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.product.irepository.iProductRepository;
import com.example.product.model.Product;

@Repository
public class ProductRepository implements iProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        try {
            return jdbcTemplate.query(sql, new ProductRowMapper());
        } catch (Exception e) {
            System.out.println("Error fetching products: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Product getProductById(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);
        } catch (Exception e) {
            System.out.println("Error fetching product for id " + id + " : " + e.getMessage());
            return null;
        }
    }

    @Override
    public String getProductNameById(Long id) {
        String sql = "SELECT name FROM product WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, id);
        } catch (Exception e) {
            System.out.println("Error fetching product name for id " + id + " : " + e.getMessage());
            return null;
        }
    }

    @Override
    public Product addProduct(Product product) {
        try {
            String sql = "INSERT INTO product (name, description, price, stock) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock()
            );

            // fetch back last inserted id
            Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
            product.setId(id);

            return product;
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Product updateProduct(Product product) {
        String sql = "UPDATE product SET name = ?, description = ?, price = ?, stock = ? WHERE id = ?";
        try {
            int updated = jdbcTemplate.update(sql,
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock(),
                    product.getId()
            );

            return updated > 0 ? product : null;
        } catch (Exception e) {
            System.out.println("Error updating product: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try {
            int deleted = jdbcTemplate.update(sql, id);
            return deleted > 0;
        } catch (Exception e) {
            System.out.println("Error deleting product with id " + id + " : " + e.getMessage());
            return false;
        }
    }

     @Override
    public List<Product> getProductsPaginated(int offset, int limit) {
    String sql = "SELECT * FROM product LIMIT ? OFFSET ?";
    try {
        // Pass parameters in the same order as in SQL: LIMIT, OFFSET
        return jdbcTemplate.query(sql, new ProductRowMapper(), limit, offset);
    } catch (Exception e) {
        System.err.println("Error fetching paginated products: " + e.getMessage());
        return new ArrayList<>(); // Return empty list instead of null
    }
}


    @Override
    public int countProducts() {
        String sql = "SELECT COUNT(*) FROM product";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception e) {
            System.out.println("Error counting products: " + e.getMessage());
            return 0;
        }
    }
}