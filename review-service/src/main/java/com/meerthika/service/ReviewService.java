package com.meerthika.service;

import com.meerthika.dto.ReviewRequest;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.UserDTO;
import com.meerthika.modal.Review;

import java.util.List;

public interface ReviewService {

    Review createReview(
            ReviewRequest req,
            UserDTO user,
            SalonDTO salon
    );

    List<Review> getReviewsBySalonId(Long salonId);

    Review updateReview(ReviewRequest req, Long reviewId, Long userId);

    void deleteReview(Long reviewId, Long userId);
}
