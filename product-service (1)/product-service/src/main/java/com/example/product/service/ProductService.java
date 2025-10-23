package com.example.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.product.dto.OrderDto;
import com.example.product.dto.ProductDto;
import com.example.product.dto.PaginatedResponse;
import com.example.product.irepository.iProductRepository;
import com.example.product.model.Product;


@Service(value = "ProductService")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class ProductService {

    private final iProductRepository _ProductRepository;
    private final ModelMapper _ModelMapper;
    private final RestClient _OrderRestClient;

    @Autowired
    public ProductService(iProductRepository productRepository,
                          ModelMapper modelMapper,
                          RestClient.Builder restClientBuilder) {
        this._ProductRepository = productRepository;
        this._ModelMapper = modelMapper;
        _OrderRestClient = restClientBuilder.baseUrl("http://localhost:8082/orders").build();
    }

    // Get all products with their orders
    public List<ProductDto> getAllProducts() {
        List<Product> products = _ProductRepository.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            OrderDto orders = GetOrdersByProductId(product.getId());
            if (orders == null) {
                ProductDto dto = _ModelMapper.map(product, ProductDto.class);
                productDtos.add(dto);
                continue;
            }

            product.setOrders(orders);
            ProductDto dto = _ModelMapper.map(product, ProductDto.class);
            productDtos.add(dto);
        }

        return productDtos;
    }

    // ✅ PAGINATION IMPLEMENTATION
     
    public PaginatedResponse<ProductDto> getProductsPaginated(int page, int size) {
    int totalElements = _ProductRepository.countProducts();
    int totalPages = (int) Math.ceil((double) totalElements / size);
    int offset = page * size;

    // repository returns List<Product>
    List<Product> products = _ProductRepository.getProductsPaginated(offset, size);

    List<ProductDto> productDtos = products.stream()
        .map(p -> _ModelMapper.map(p, ProductDto.class))
        .collect(Collectors.toList());

    // build the PaginatedResponse
    PaginatedResponse<ProductDto> response = new PaginatedResponse<>();
    response.setPage(page);
    response.setSize(size);
    response.setTotalElements(totalElements);
    response.setTotalPages(totalPages);
    response.setProducts(productDtos);

    return response; // ✅ now type matches
}



    // Get product by ID with its orders
    public ProductDto getProductById(Long productId) {
        Product product = _ProductRepository.getProductById(productId);
        if (product == null) {
            return null;
        }

        OrderDto orders = GetOrdersByProductId(productId);
        if (orders != null) {
            product.setOrders(orders);
        }

        return _ModelMapper.map(product, ProductDto.class);
    }

    // Add product
    public ProductDto addProduct(ProductDto productDto) {
        Product product = _ModelMapper.map(productDto, Product.class);
        Product savedProduct = _ProductRepository.addProduct(product);
        return savedProduct != null ? _ModelMapper.map(savedProduct, ProductDto.class) : null;
    }

    // Update product
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = _ModelMapper.map(productDto, Product.class);
        Product updatedProduct = _ProductRepository.updateProduct(product);
        return updatedProduct != null ? _ModelMapper.map(updatedProduct, ProductDto.class) : null;
    }

    // Delete product
    public boolean deleteProduct(Long productId) {
        return _ProductRepository.deleteProduct(productId);
    }

    public OrderDto GetOrdersByProductId(Long productId) {
        try {
            OrderDto dto = _OrderRestClient.get()
                    .uri("/order/{id}", productId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<OrderDto>() {});
            return dto;
        } catch (Exception e) {
            System.out.println("Error fetching orders: " + e.getMessage());
            return null;
        }
    }

    public void AddOrders(List<OrderDto> orders) {
        try {
            _OrderRestClient.post()
                    .uri("/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(orders)
                    .retrieve()
                    .body(Void.class);
        } catch (Exception e) {
            System.out.println("Error while adding orders: " + e.getMessage());
        }
    }

    public void DeleteOrdersByProductId(Long productId) {
        try {
            _OrderRestClient.delete()
                    .uri("/delete/product/{productId}", productId)
                    .retrieve()
                    .body(Void.class);
        } catch (Exception e) {
            System.out.println("Error while deleting orders: " + e.getMessage());
        }
    }
}
