package com.example.bookstore2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bookstore2.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
}
