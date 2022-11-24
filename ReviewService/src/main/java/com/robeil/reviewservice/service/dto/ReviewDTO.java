package com.robeil.reviewservice.service.dto;

import com.robeil.reviewservice.model.Book;
import com.robeil.reviewservice.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ReviewDTO {

    private int reviewId;
    @Range(min = 0, max = 5)
    private int reviewRate;
    private Book book;
    private Customer customerName;
}
