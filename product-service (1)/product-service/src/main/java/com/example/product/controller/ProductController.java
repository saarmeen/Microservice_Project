package com.example.product.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    // GET /products
    @GetMapping
    public String getAllProducts() {
        return "All products endpoint working!";
    }

    // GET /products/{id}
    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id) {
        return "Product details for ID " + id;
    }

    // POST /products
    @PostMapping
    public String addProduct() {
        return "Add product endpoint working!";
    }

    // PUT /products/{id}
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id) {
        return "Update product endpoint working for ID " + id;
    }

    // DELETE /products/{id}
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "Delete product endpoint working for ID " + id;
    }
}
