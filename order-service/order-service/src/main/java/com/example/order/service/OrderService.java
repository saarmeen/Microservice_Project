package com.example.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.dto.OrderDto;
import com.example.order.dto.PaginatedResponse;
import com.example.order.iRepository.iOrderRepository;
import com.example.order.model.Order;

@Service(value = "OrderService")
public class OrderService {

    private final iOrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderService(iOrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    
    public PaginatedResponse<OrderDto> getOrdersPaginated(int page, int size) {
    int totalElements = orderRepository.countOrders();
    int totalPages = (int) Math.ceil((double) totalElements / size);
    int offset = page * size;

    // repository returns List<Product>
    List<Order> orders = orderRepository.getOrdersPaginated(offset, size);

    List<OrderDto> OrderDtos = orders.stream()
        .map(p -> _ModelMapper.map(p, OrderDto.class))
        .collect(Collectors.toList());

    // build the PaginatedResponse
    PaginatedResponse<OrderDto> response = new PaginatedResponse<>();
    response.setPage(page);
    response.setSize(size);
    response.setTotalElements(totalElements);
    response.setTotalPages(totalPages);
    response.setOrders(OrderDtos);

    return response; // ✅ now type matches
}

    // Get all orders
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.getAllOrders();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order o : orders) {
            OrderDto dto = modelMapper.map(o, OrderDto.class);
            orderDtos.add(dto);
        }
        return orderDtos;
    }

    // Get order by Id
    public OrderDto getOrderById(Long orderId) {
        Order o = orderRepository.getOrderById(orderId);
        if (o == null) {
            return null;
        }
        OrderDto dto = modelMapper.map(o, OrderDto.class);
        return dto;
    }

    // Get order status by Id
    public OrderDto getOrderStatusById(Long orderId) {
        Order o = orderRepository.getOrderById(orderId); // fetch full order
        if (o == null) {
            return null;
        }
        OrderDto dto = new OrderDto();
        dto.setOrderId(o.getId());
        dto.setStatus(o.getStatus());  // only map required field
        return dto;
    }


    // Add new order
    public OrderDto addOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        Order savedOrder = orderRepository.addOrder(order);
        return savedOrder != null ? modelMapper.map(savedOrder, OrderDto.class) : null;
    }

    // Update order
    public OrderDto updateOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        Order updatedOrder = orderRepository.updateOrder(order);
        return updatedOrder != null ? modelMapper.map(updatedOrder, OrderDto.class) : null;
    }

    // Delete order
    // Delete order (returns DTO)
    public OrderDto deleteOrder(Long orderId) {
        Order o = orderRepository.getOrderById(orderId);
        if (o == null) {
            OrderDto dto = new OrderDto();
            dto.setOrderId(orderId);
            dto.setMessage("Order with ID = " + orderId + " not found.");
            return dto;
        }

        boolean deleted = orderRepository.deleteOrder(orderId);
        OrderDto dto = modelMapper.map(o, OrderDto.class);
        if (deleted) {
            dto.setMessage("This order, Order_ID = " + orderId + " has been deleted.");
        } else {
            dto.setMessage("Failed to delete Order with ID = " + orderId);
        }
        return dto;
    }

}
