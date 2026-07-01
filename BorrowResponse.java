package com.learnapi.test.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BorrowResponse {

    private Long id;
    private Long bookId;
    private String book;
    private Long memberId;
    private String memberName;
    private LocalDateTime borrowDate;
}