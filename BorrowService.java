package com.learnapi.test.service;
import com.learnapi.test.dto.BorrowRequest;
import com.learnapi.test.dto.BorrowResponse;
import java.util.List;

public interface BorrowService {
    String create(BorrowRequest borrowRequest);
    String update(Long id, BorrowRequest borrowRequest);
    void delete(Long id, BorrowRequest borrowRequest);
    BorrowResponse getById(Long id);
    List<BorrowResponse>getAll();

}
