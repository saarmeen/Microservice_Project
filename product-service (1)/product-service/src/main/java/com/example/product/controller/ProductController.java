package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // http://localhost:8081/products/allproducts
    @GetMapping(path = "/allproducts")
    public List<ProductDto> getAllProducts() {
        return _productService.getAllProducts();
    }

    // http://localhost:8081/products/product/1
    @GetMapping(path = "/product/{id}")
    public ProductDto getProductById(@PathVariable("id") Long id) {
        return _productService.getProductById(id);
    }

    // http://localhost:8081/products/product/productname/1
    @GetMapping(path = "/product/productname/{id}")
    public String getProductNameById(@PathVariable("id") Long id) {
        return _productService.getProductNameById(id);
    }

    // http://localhost:8081/products/add
    @PostMapping(path = "/add")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return _productService.addProduct(productDto);
    }

    // http://localhost:8081/products/update
    @PutMapping(path = "/update")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return _productService.updateProduct(productDto);
    }

    // http://localhost:8081/products/delete/1
    @DeleteMapping(path = "/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        boolean deleted =  _productService.deleteProduct(id);
         if (deleted) {
         return "This product, Product_ID = " + id + " has been deleted.";
    } else {
        return "Product with ID = " + id + " not found.";
    }
    }
}
