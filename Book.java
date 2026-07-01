package com.learnapi.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private   Long id;
    private String bookTitle;
    private String bookAuthor;
    @Column(name = "book_price")
    private Double bookPrice;
    private String bookDescription;
}
