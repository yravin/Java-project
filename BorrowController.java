package com.learnapi.test.controller;
import com.learnapi.test.dto.*;
import com.learnapi.test.service.BorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody BorrowRequest borrowRequest){
        log.info("Create book with request: {}", borrowRequest);
        borrowService.create(borrowRequest);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody BorrowRequest bookRequest) {
        log.info("Update todo id: {} with request: {}", id, bookRequest);
        borrowService.update(id, bookRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, @RequestBody BorrowRequest borrowRequest) {
        log.info("Delete todo id: {}", id);
        borrowService.delete(id, borrowRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowResponse> getBookById(@PathVariable Long id) {
        log.info("Get todo by id: {}", id);
        BorrowResponse borrowResponse = borrowService.getById(id);
        return ResponseEntity.ok(borrowResponse);
    }

    @GetMapping
    public ResponseEntity<List<BorrowResponse>> getAllBooks() {
        log.info("Get all book");
        List<BorrowResponse> books = borrowService.getAll();
        return ResponseEntity.ok(books);
    }
}


