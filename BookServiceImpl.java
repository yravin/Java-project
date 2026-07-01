package com.learnapi.test.service.impl;
import com.learnapi.test.dto.BookRequest;
import com.learnapi.test.dto.BookResponse;
import com.learnapi.test.model.Book;
import com.learnapi.test.repository.BookRepository;
import com.learnapi.test.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl  implements BookService {
    private  final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(BookRequest bookRequest) {
        log.info("Creating new book: {}", bookRequest.getBookTitle());
        Book book = new Book();
        book.setBookTitle(bookRequest.getBookTitle());
        book.setBookAuthor(bookRequest.getBookAuthor());
        book.setBookPrice(bookRequest.getBookPrice());
        book.setBookDescription(bookRequest.getBookDescription());
        bookRepository.save(book);
    }

    @Override
    public void update(Long id, BookRequest bookRequest) {
        log.info("Updating book with id: {}", id);

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        book.setBookTitle(bookRequest.getBookTitle());
        book.setBookAuthor(bookRequest.getBookAuthor());
        book.setBookPrice(bookRequest.getBookPrice());
        book.setBookDescription(bookRequest.getBookDescription());
        bookRepository.save(book);
    }

    @Override
    public void delete(Long id, BookRequest bookRequest) {
        bookRepository.deleteById(id);

    }

    @Override
    public BookResponse getById(Long id) {
       BookResponse bookResponse = new BookResponse();
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            log.info("information not found");
            return bookResponse;
        }
        bookResponse.setId(book.get().getId());
        bookResponse.setBookTitle(book.get().getBookTitle());
        bookResponse.setBookAuthor(book.get().getBookAuthor());
        bookResponse.setBookPrice(book.get().getBookPrice());
        bookResponse.setBookDescription(book.get().getBookDescription());
        return bookResponse;
    }

    @Override
    public List<BookResponse> getAll() {
        log.info("Fetching all books");
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    // Helper method សម្រាប់បម្លែងពី Book (Model) ទៅជា BookResponse (DTO)
    private BookResponse mapToResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setBookTitle(book.getBookTitle());
        response.setBookAuthor(book.getBookAuthor());
        response.setBookPrice(book.getBookPrice() != null ? book.getBookPrice() : 0.0); // ← fix
        response.setBookDescription(book.getBookDescription());
        return response;
    }
}

