package com.learnapi.test.service;

import com.learnapi.test.dto.BookRequest;
import com.learnapi.test.dto.BookResponse;

import java.util.List;

public interface BookService {
    void create(BookRequest bookRequest);
    void update(Long id, BookRequest bookRequest);
    void delete(Long id, BookRequest bookRequest);
    BookResponse getById(Long id);
    List<BookResponse> getAll();
}
