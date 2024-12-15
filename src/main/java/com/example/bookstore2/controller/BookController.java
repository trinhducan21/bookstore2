package com.example.bookstore2.controller;

import java.util.List;
import com.example.bookstore2.model.Book;
import com.example.bookstore2.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/book{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/category{categoryId}")
    public ResponseEntity<Book> createBook(@RequestBody Book book, @PathVariable Long categoryId) {
        Book savedBook = bookService.createBook(book, categoryId);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/book{id}/category{categoryId}")  // thêm categoryId vào path
    public Book updateBook(@PathVariable Long id, @PathVariable Long categoryId, @RequestBody Book book){
        book.setId(id);
        return bookService.updateBook(book, categoryId);  // truyền thêm categoryId
    }

    @DeleteMapping("/book{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Xóa sách thành công");
    }
}
