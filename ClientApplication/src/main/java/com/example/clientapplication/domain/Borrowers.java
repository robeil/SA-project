package com.example.clientapplication.domain;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Borrowers {

    private List<Borrower> borrowers;
}
