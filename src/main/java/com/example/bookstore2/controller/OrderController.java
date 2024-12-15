package com.example.bookstore2.controller;

import com.example.bookstore2.model.Order;
import com.example.bookstore2.repository.OrderRepository;
import com.example.bookstore2.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Map<String, Long> request) {
        Long customerId = request.get("customerId");
        Long bookId = request.get("bookId");
        Order newOrder = orderService.createOrder(customerId, bookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable Long orderId,
            @RequestBody Map<String, Long> request) {
        Long customerId = request.get("customerId");
        Long bookId = request.get("bookId");
        Order updatedOrder = orderService.updateOrder(orderId, customerId, bookId);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
