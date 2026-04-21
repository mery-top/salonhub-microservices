package com.meerthika.service.impl;

import com.meerthika.dto.ReviewRequest;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.UserDTO;
import com.meerthika.modal.Review;
import com.meerthika.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    @Override
    public Review createReview(ReviewRequest req, UserDTO user, SalonDTO salon) {
        return null;
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
