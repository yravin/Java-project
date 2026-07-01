package com.learnapi.test.service.impl;

import com.learnapi.test.dto.BorrowRequest;
import com.learnapi.test.dto.BorrowResponse;
import com.learnapi.test.model.BorrowBook;
import com.learnapi.test.repository.BorrowRepository;
import com.learnapi.test.service.BorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;

    public BorrowServiceImpl(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    // ------------- Post data ------------------------
    @Override
    public String create(BorrowRequest borrowRequest) {
        BorrowBook borrow = new BorrowBook();
        log.info("Create Borrow Book");
        borrow.setBook(borrowRequest.getBook());
        borrow.setMember(borrowRequest.getMember());
        borrowRepository.save(borrow);
        return "Create data Successfully";
    }
    //------------------- update Data ------------------------------
    @Override
    public String update(Long id, BorrowRequest borrowRequest) {
        log.info("Updating book with id: {}", id);
        BorrowBook borrowBook = borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow Book not found with id: " + id));
        borrowBook.setBook(borrowRequest.getBook());
        borrowBook.setMember(borrowRequest.getMember());
        borrowRepository.save(borrowBook);
        return "Update data Successfully";
    }

    //------------------------ Delete Data -------------------------------------
    @Override
    public void delete(Long id, BorrowRequest borrowRequest) {
        log.info("delete Borrow Book");
        borrowRepository.deleteById(id);
    }

    //----------------------------- get data by ID --------------------------
    @Override
    public BorrowResponse getById(Long id) {
        log.info("Get Borrow Book with id: {}", id);

        return borrowRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Information not found with id: " + id));
    }

    //-------------------------- get Data ALL -----------------------------
    @Override
    public List<BorrowResponse> getAll() {
        log.info("Fetching all borrow records");
        List<BorrowBook> borrowBooks = borrowRepository.findAll();

        return borrowBooks.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private BorrowResponse mapToResponse(BorrowBook borrowBook) {
        BorrowResponse response = new BorrowResponse();
        response.setId(borrowBook.getId());
        response.setBook(borrowBook.getBook().getBookTitle());
        response.setMemberId(borrowBook.getMember().getId());
        response.setMemberName(borrowBook.getMember().getName());
        response.setBorrowDate(borrowBook.getBorrowDate());
        return response;
    }
}