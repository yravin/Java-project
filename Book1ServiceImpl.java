package com.learnapi.test.service.impl;

import com.learnapi.test.dto.Book1Request;
import com.learnapi.test.dto.Book1Response;
import com.learnapi.test.model.Book1;
import com.learnapi.test.repository.Book1Repository;
import com.learnapi.test.service.Book1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class Book1ServiceImpl implements Book1Service {
    //ហៅ Class Repository មកប្រេី
    private  final Book1Repository book1Repository;

    public Book1ServiceImpl(Book1Repository book1Repository) {
        //សូមយកតម្លៃពី Parameter (ខាងស្តាំ) ទៅដាក់បញ្ចូលក្នុង Variable របស់ Class (ខាងឆ្វេង)
        this.book1Repository = book1Repository;
    }

    @Override
    public void create(Book1Request book1Request) {
        log.info("Book create ");
        Book1 book1 = new Book1();
        book1.setBookTitle(book1Request.getBookTitle());
        book1.setBookAuthor(book1Request.getBookAuthor());
        book1.setBookISBN(book1Request.getBookISBN());
        book1Repository.save(book1);
    }


    @Override
    public void update(Long id, Book1Request book1Request) {
        log.info("Updating book with id");
        Book1 book1 = book1Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book1.setBookTitle(book1Request.getBookTitle());
        book1.setBookAuthor(book1Request.getBookAuthor());
        book1.setBookISBN(book1Request.getBookISBN());
        book1Repository.save(book1);
    }

    @Override
    public void delete(Long id) {
        book1Repository.deleteById(id);

    }

    @Override
    public Book1Response getById(Long id) {
        Book1 book1 = book1Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        Book1Response book1Response = new Book1Response();
        book1Response.setId(book1.getId());
        book1Response.setBookAuthor(book1.getBookAuthor());
        book1Response.setBookTitle(book1.getBookTitle());
        book1Response.setBookISBN(book1.getBookISBN());
        return book1Response;
    }

    @Override
    public List<Book1Response> getAll() {

        List<Book1> books = book1Repository.findAll();

        return books.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }
    private Book1Response mapToResponse(Book1 book1){
        Book1Response response = new Book1Response();
        response.setId(book1.getId());
        //book1.getBookTitle() (GET): ទាញយកចំណងជើងសៀវភៅពី Entity ដែលបានមកពី Database
        //response.setBookTitle(...) (SET): យកចំណងជើងនោះទៅញាត់ចូលក្នុងប្រអប់ Response
        response.setBookTitle(book1.getBookTitle());
        response.setBookAuthor(book1.getBookAuthor());
        response.setBookISBN(book1.getBookISBN());
        return response;
    }
}
