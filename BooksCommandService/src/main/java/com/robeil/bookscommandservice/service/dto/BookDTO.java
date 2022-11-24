package com.robeil.bookscommandservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class BookDTO {

    private String isbn;
    private String bookTitle;
    private String description;
    private String authorName;
    private List<ReviewDTO> reviews;


}

