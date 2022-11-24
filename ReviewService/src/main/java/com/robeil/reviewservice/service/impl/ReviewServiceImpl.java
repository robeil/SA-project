package com.robeil.reviewservice.service.impl;

import com.robeil.reviewservice.integration.ChangeEvent;
import com.robeil.reviewservice.integration.Sender;
import com.robeil.reviewservice.model.Book;
import com.robeil.reviewservice.model.Review;
import com.robeil.reviewservice.repositroy.ReviewRepository;
import com.robeil.reviewservice.service.ReviewService;
import com.robeil.reviewservice.service.dto.ReviewDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private Sender sender;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addReviewToBook(Review review) {
        /**
         * publishing new added review
         */
        ChangeEvent<Review> changedReview = new ChangeEvent<Review>("addReview", review);
        sender.send(changedReview);
        System.out.println("Publishing addReviewToBook from ReviewService");
        reviewRepository.save(review);
    }
}
