package com.robeil.booksqueryservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Book {

    @Id
    private String isbn;
    private String bookTitle;
    private String description;
    private String authorName;
    private List<Review> reviews;
}
