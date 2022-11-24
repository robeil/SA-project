package com.robeil.borrowingsservice.service.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BorrowerDTO {

    private int borrowerNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private BookDTO bookDTO;
    private CustomerDTO customerDTO;
}
