package com.learnapi.test.service;

import com.learnapi.test.dto.Book1Request;
import com.learnapi.test.dto.Book1Response;

import java.util.List;

public interface Book1Service {

    void create(Book1Request book1Request);

    void update(Long id, Book1Request book1Request);

    void delete(Long id);

    Book1Response getById(Long id);

    List<Book1Response> getAll();
}
