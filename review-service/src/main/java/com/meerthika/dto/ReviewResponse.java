package com.meerthika.dto;


import lombok.Data;

@Data
public class ReviewResponse {
    private Long id;
    private String reviewText;
    private double rating;
    private Long salonId;
    private UserDTO user;
}
