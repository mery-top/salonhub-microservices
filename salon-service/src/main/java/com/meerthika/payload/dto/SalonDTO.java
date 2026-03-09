package com.meerthika.payload.dto;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

public class SalonDTO {


    private Long id;

    private String name;

    private List<String> images;

    private String address;

    private String phoneNumber;

    private String email;

    private String city;

    private Long ownerId;


    private LocalTime openTime;

    private LocalTime closeTime;
}
