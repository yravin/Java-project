package com.learnapi.test.controller;
import com.learnapi.test.dto.Book1Request;
import com.learnapi.test.dto.Book1Response;
import com.learnapi.test.dto.BookRequest;
import com.learnapi.test.dto.BookResponse;
import com.learnapi.test.service.Book1Service;
import com.learnapi.test.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/library")
public class BookController1 {
    @Autowired
    private Book1Service book1Service;

    @GetMapping
    public ResponseEntity<List<Book1Response>> getAllBooks() {
        log.info("Get all book");
        List<Book1Response> books = book1Service.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book1Response> getBookById(@PathVariable Long id) {
        log.info("Get todo by id: {}", id);
        Book1Response book1Response = book1Service.getById(id);
        return ResponseEntity.ok(book1Response);
    }

    @PostMapping
    public ResponseEntity<Object> createBook(@RequestBody Book1Request book1Request){
        log.info("Create book with request: {}", book1Request);
        book1Service.create(book1Request);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Book1Request book1Request) {
        log.info("Update todo id: {} with request: {}", id, book1Request);
        book1Service.update(id, book1Request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable Long id, @RequestBody Book1Request book1Request) {
        log.info("Delete todo id: {}", id);
        book1Service.delete(id);
        return ResponseEntity.ok().build();
    }
}
