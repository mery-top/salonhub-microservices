package com.meerthika.service.impl;

import com.meerthika.dto.ReviewRequest;
import com.meerthika.dto.ReviewResponse;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.UserDTO;
import com.meerthika.mapper.ReviewMapper;
import com.meerthika.modal.Review;
import com.meerthika.repository.ReviewRepository;
import com.meerthika.service.ReviewService;
import com.meerthika.service.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserFeignClient userFeignClient;
    private final ReviewMapper reviewMapper;

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
    public List<ReviewResponse> getReviewsBySalonId(Long salonId) {

        List<Review> reviews = reviewRepository.findBySalonId(salonId);

        return reviews.stream().map(review -> {

            UserDTO user = null;
            try {
                user = userFeignClient
                        .getUserById(review.getUserId())
                        .getBody();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return reviewMapper.mapToResponse(review, user);

        }).toList();
    }
    private Review getReviewById(Long id) throws Exception{
        return reviewRepository.findById(id).orElseThrow(
                () -> new Exception("review not exist")
        );
    }

    @Override
    public Review updateReview(ReviewRequest req, Long reviewId, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if(!review.getUserId().equals(userId)){
            throw new Exception("You don't have permission to update this review");
        }

        review.setReviewText(req.getReviewText());
        review.setRating(req.getRating());
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if(!review.getUserId().equals(userId)){
            throw new Exception("You don't have permission to delete this review");
        }
        reviewRepository.delete(review);
    }
}
