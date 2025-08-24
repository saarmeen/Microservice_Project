package com.example.product.irepository;

import java.util.List;

import com.example.product.model.Product;

public interface iProductRepository {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    String getProductNameById(Long id);
    Product addProduct(Product product);
    Product updateProduct(Product product);
    boolean deleteProduct(Long id);
}
