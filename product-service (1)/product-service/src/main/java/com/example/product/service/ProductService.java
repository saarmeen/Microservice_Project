package com.example.product.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.dto.ProductDto;
import com.example.product.irepository.iProductRepository;
import com.example.product.model.Product;

@Service(value = "ProductService")
public class ProductService {

    private final iProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(iProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    // Get all products
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p : products) {
            ProductDto dto = modelMapper.map(p, ProductDto.class);
            productDtos.add(dto);
        }
        return productDtos;
    }

    // Get product by Id
    public ProductDto getProductById(Long productId) {
        Product p = productRepository.getProductById(productId);
        if (p == null) {
            return null;
        }
        return modelMapper.map(p, ProductDto.class);
    }

    // Get product name by Id
    public String getProductNameById(Long productId) {
        return productRepository.getProductNameById(productId);
    }

    // Add new product
    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.addProduct(product);
        return savedProduct != null ? modelMapper.map(savedProduct, ProductDto.class) : null;
    }

    // Update product
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product updatedProduct = productRepository.updateProduct(product);
        return updatedProduct != null ? modelMapper.map(updatedProduct, ProductDto.class) : null;
    }

    // Delete product
    public boolean deleteProduct(Long productId) {
        return productRepository.deleteProduct(productId);
    }
}
