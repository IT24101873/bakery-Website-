package com.bakery.BakeryWebsite.services;


import com.bakery.BakeryWebsite.models.Review;
import com.bakery.BakeryWebsite.repository.ReviewRepository;

import java.util.List;

public class ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewService() {
        this.reviewRepository = new ReviewRepository();
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(String id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(String id, Review review) {
        Review existing = reviewRepository.findById(id);
        if (existing != null) {
            review.setId(id);
            return reviewRepository.save(review);
        }
        return null;
    }

    public void deleteReview(String id) {
        reviewRepository.delete(id);
    }
}

