package com.meerthika.service.impl;

import com.meerthika.dto.ReviewRequest;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.UserDTO;
import com.meerthika.modal.Review;
import com.meerthika.repository.ReviewRepository;
import com.meerthika.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(ReviewRequest req, UserDTO user, SalonDTO salon) {

        Review review = new Review();
        review.setReviewText(req.getReviewText());
        review.setRating(req.getRating());
        review.setUserId(user.getId());
        review.setSalonId(salon.getId());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsBySalonId(Long salonId) {
        return List.of();
    }

    @Override
    public Review updateReview(ReviewRequest req, Long reviewId, Long userId) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) {

    }
}
