package com.meerthika.controller;

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




}
