package com.example.bookstore2.repository;

import org.springframework.stereotype.Repository;
import com.example.bookstore2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
