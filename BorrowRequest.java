package com.learnapi.test.dto;

import com.learnapi.test.model.Book1;
import com.learnapi.test.model.Member;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class BorrowRequest {
    @JoinColumn(name = "book_id", nullable = false)
    private Book1 book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}
