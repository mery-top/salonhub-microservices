package com.meerthika.controller;

import com.meerthika.dto.ApiResponse;
import com.meerthika.dto.ReviewRequest;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.UserDTO;
import com.meerthika.modal.Review;
import com.meerthika.service.ReviewService;
import com.meerthika.service.client.SalonFeignClient;
import com.meerthika.service.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserFeignClient userFeignClient;
    private final SalonFeignClient salonFeignClient;

    @PostMapping("/salon/{salonId}")
    public ResponseEntity<Review> createReview(
            @PathVariable Long salonId,
            @RequestBody ReviewRequest req,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {

        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();
        SalonDTO salon = salonFeignClient.getSalonsById(salonId).getBody();

        Review review = reviewService.createReview(req, user, salon);

        return ResponseEntity.ok(review);
    }
    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<Review>> getReviewById(
            @PathVariable Long salonId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        SalonDTO salon = salonFeignClient.getSalonsById(salonId).getBody();
        List<Review> reviews = reviewService.getReviewsBySalonId(salon.getId());

        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();

        Review review = reviewService.updateReview(req, reviewId, user.getId());

        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();

        reviewService.deleteReview(reviewId, user.getId());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Review Deleted");

        return ResponseEntity.ok(apiResponse);
    }






}
