package com.example.bookstore2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookstore2.model.Order;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.customer LEFT JOIN FETCH o.books WHERE o.id = :id")
    Optional<Order> findById(@Param("id") Long id);
}
