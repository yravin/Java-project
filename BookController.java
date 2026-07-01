package com.learnapi.test.controller;

import com.learnapi.test.dto.BookRequest;
import com.learnapi.test.dto.BookResponse;
import com.learnapi.test.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping
    public ResponseEntity<Object> createBook(@RequestBody BookRequest bookRequest){
        log.info("Create book with request: {}", bookRequest);
        bookService.create(bookRequest);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        log.info("Get all book");
        List<BookResponse> books = bookService.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        log.info("Get todo by id: {}", id);
        BookResponse todoResponse = bookService.getById(id);
        return ResponseEntity.ok(todoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        log.info("Update todo id: {} with request: {}", id, bookRequest);
        bookService.update(id, bookRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        log.info("Delete todo id: {}", id);
        bookService.delete(id, bookRequest);
        return ResponseEntity.ok().build();
    }
}
