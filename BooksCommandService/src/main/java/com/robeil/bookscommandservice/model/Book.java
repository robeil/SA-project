package com.robeil.bookscommandservice.model;

import lombok.*;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Book {

    @Id
    private String isbn;
    private String bookTitle;
    private String description;
    private String authorName;
    private List<Review> reviews;

}
