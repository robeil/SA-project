package com.example.clientapplication.domain;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    private String isbn;
    private String bookTitle;
    private String description;
    private String authorName;
    private List<Review> reviews;

}
