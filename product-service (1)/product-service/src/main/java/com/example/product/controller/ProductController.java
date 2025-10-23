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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dto.PaginatedResponse;
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

     // ✅ PAGINATION ENDPOINT
    // Example: http://localhost:8081/products/paginated?page=0&size=3
     @GetMapping("/paginated")
    public PaginatedResponse<ProductDto> getPaginatedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return _productService.getProductsPaginated(page, size);
    }


    // http://localhost:8081/products/allproducts
    @GetMapping(path = "/allproducts")
    public List<ProductDto> getAllProducts() {
        return _productService.getAllProducts();
    }

    // http://localhost:8081/products/product/1
    @GetMapping(path = "/product/{id}")
    public ProductDto getProductById(@PathVariable("id") Long productId) {
        return _productService.getProductById(productId);
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
    public String deleteProduct(@PathVariable("id") Long productId) {
        boolean deleted =  _productService.deleteProduct(productId);
         if (deleted) {
         return "This product, Product_ID = " + productId + " has been deleted.";
    } else {
        return "Product with ID = " + productId + " not found.";
    }
    }
    
}
