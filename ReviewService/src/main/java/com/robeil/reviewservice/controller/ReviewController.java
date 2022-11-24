package com.robeil.reviewservice.controller;

import com.robeil.reviewservice.integration.ChangeEvent;
import com.robeil.reviewservice.integration.Sender;
import com.robeil.reviewservice.model.Review;
import com.robeil.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public void addNEwReview(@RequestBody Review review){
        reviewService.addReviewToBook(review);
    }

}
