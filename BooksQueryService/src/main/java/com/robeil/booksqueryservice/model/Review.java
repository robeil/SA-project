package com.robeil.booksqueryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"reviewId","book","customer"})
public class Review {

    private String isbn;
    @Range(min = 0, max = 5)
    private int reviewRate;

}
