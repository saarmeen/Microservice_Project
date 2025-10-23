package com.example.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.dto.OrderDto;
import com.example.order.dto.PaginatedResponse;
import com.example.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Example: http://localhost:8081/products/paginated?page=0&size=3
     @GetMapping("/paginated")
    public PaginatedResponse<OrderDto> getPaginatedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return orderService.getOrdersPaginated(page, size);
    }

    // ✅ Get all orders
    // GET http://localhost:8082/orders/allOrders
    @GetMapping("/allOrders")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    // ✅ Get order by ID
    // GET http://localhost:8082/orders/1
    @GetMapping("/order/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // http://localhost:8082/orders/status/1
    @GetMapping("/status/{id}")
    public ResponseEntity<OrderDto> getOrderStatus(@PathVariable Long id) {
        OrderDto dto = orderService.getOrderStatusById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }


    // ✅ Place a new order
    // POST http://localhost:8082/orders/add
    @PostMapping("/add")
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }

    // ✅ Update an existing order
    // PUT http://localhost:8082/orders/update
    @PutMapping("/update")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    // ✅ Delete an order
    // DELETE http://localhost:8082/orders/delete/1
    @DeleteMapping("/delete/{id}")
    public OrderDto deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }

}
