package com.robeil.borrowingsservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Borrower {

    @Id
    private int borrowerNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Customer customer;
    private List<Book> books;

}
