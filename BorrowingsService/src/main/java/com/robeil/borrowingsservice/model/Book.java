package com.robeil.borrowingsservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"description","authorName"})
public class Book {

    private String isbn;
    private String bookTitle;

}

