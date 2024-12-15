package com.example.bookstore2.service;

import com.example.bookstore2.model.Book;
import com.example.bookstore2.model.Category;
import com.example.bookstore2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Book> getAllBooks(){ return bookRepository.findAll(); }

    public Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public Book createBook(Book book, Long categoryId) {
    // Kiểm tra và set category
        Category category = categoryService.getById(categoryId);
        if (category == null) {
            throw new RuntimeException("Category không tồn tại");
        }
        book.setCategory(category);

        return bookRepository.save(book);
    }

    public Book updateBook(Book book, Long categoryId) {
        // Kiểm tra book có tồn tại không
        Book existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sách với id: " + book.getId()));

        // Lấy và kiểm tra category
        Category category = categoryService.getById(categoryId);
        if (category == null) {
            throw new RuntimeException("Category không tồn tại");
        }

        // Cập nhật thông tin
        book.setCategory(category);

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
