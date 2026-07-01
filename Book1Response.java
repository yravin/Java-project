package com.learnapi.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book1Response {
    private Long id;
    @JsonProperty("book_title")
    private String bookTitle;
    @JsonProperty("book_author")
    private  String bookAuthor;
    @JsonProperty("book_isbn")
    private String bookISBN;
}
