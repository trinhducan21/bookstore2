package com.example.bookstore2.service;

import com.example.bookstore2.model.Category;
import com.example.bookstore2.model.Customer;
import com.example.bookstore2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }


    public Category updateCategory(Long id, Category categoryDetails){
        Category category = getById(id);
        category.setName(categoryDetails.getName());
        return categoryRepository.save(category);
    }

    public Category getById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCategory(Long id){
        Category category = getById(id);
        categoryRepository.delete(category);
    }
}
