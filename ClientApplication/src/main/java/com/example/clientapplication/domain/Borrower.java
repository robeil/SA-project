package com.example.clientapplication.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Borrower {

    private int borrowerNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Customer customer;
    private List<Book> books;

}
