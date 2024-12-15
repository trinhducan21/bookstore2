package com.example.bookstore2.service;

import com.example.bookstore2.model.Book;
import com.example.bookstore2.model.Customer;
import com.example.bookstore2.model.Order;
import com.example.bookstore2.repository.CustomerRepository;
import com.example.bookstore2.repository.OrderRepository;
import com.example.bookstore2.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerRepository customerRepository;
    private EntityManager entityManager;

    public Order createOrder(Long customerId, Long bookId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setCustomer(customer);
        order.getBooks().add(book);
        order.setPrice(book.getPrice());
        order.setTotalAmount(book.getPrice());

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return entityManager.createQuery(
                 "SELECT o FROM Order o " +
                 "LEFT JOIN FETCH o.customer " +
                 "LEFT JOIN FETCH o.books " +
                 "WHERE o.id = :id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Order updateOrder(Long orderId, Long customerId, Long bookId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        order.setCustomer(customer);
        order.getBooks().clear();  // Xóa sách cũ
        order.getBooks().add(book);  // Thêm sách mới
        order.setPrice(book.getPrice());
        order.setTotalAmount(book.getPrice());

        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
}
