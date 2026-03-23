package com.meerthika.dto;

import com.meerthika.payload.dto.UserDTO;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class SalonDTO {


    private Long id;

    private String name;

    private List<String> images;

    private String address;

    private String phoneNumber;

    private String email;

    private String city;

    private Long ownerId;

    //we add this user details that's why dto is required
    private UserDTO owner;

    private LocalTime openTime;

    private LocalTime closeTime;
}
