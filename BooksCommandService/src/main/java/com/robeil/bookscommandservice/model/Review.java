package com.robeil.bookscommandservice.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {

    private int reviewId;
    @Range(min = 0, max = 5)
    private int reviewRate;
    private String isbn;

}
