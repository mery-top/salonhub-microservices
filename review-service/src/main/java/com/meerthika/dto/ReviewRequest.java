package com.meerthika.dto;

import lombok.Data;

@Data
public class ReviewRequest {

    private String reviewText;
    private double rating;
}
