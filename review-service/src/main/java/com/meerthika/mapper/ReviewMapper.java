package com.meerthika.mapper;

import com.meerthika.dto.ReviewResponse;
import com.meerthika.dto.UserDTO;
import com.meerthika.modal.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewResponse mapToResponse(Review review, UserDTO user) {

        ReviewResponse res = new ReviewResponse();
        res.setId(review.getId());
        res.setReviewText(review.getReviewText());
        res.setRating(review.getRating());
        res.setSalonId(review.getSalonId());
        res.setUser(user);

        return res;
    }
}
