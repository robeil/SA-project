package com.example.clientapplication.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Review {

    private int reviewId;
    private int reviewRate;
    private String isbn;

}
