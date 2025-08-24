package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dto.ProductDto;
import com.example.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService _productService;

    @Autowired
    public ProductController(ProductService productService) {
        this._productService = productService;
    }

    // GET /products/allproducts
    @GetMapping(path = "/allproducts")
    public List<ProductDto> getAllProducts() {
        return _productService.getAllProducts();
    }

    // GET /products/product/{id}
    @GetMapping(path = "/product/{id}")
    public ProductDto getProductById(@PathVariable("id") Long id) {
        return _productService.getProductById(id);
    }

    // GET /products/product/productname/{id}
    @GetMapping(path = "/product/productname/{id}")
    public String getProductNameById(@PathVariable("id") Long id) {
        return _productService.getProductNameById(id);
    }

    // POST /products/product
    @PostMapping(path = "/product")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return _productService.addProduct(productDto);
    }
}
