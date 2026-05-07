package com.meerthika.dto;

import com.meerthika.domain.BookingStatus;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Data
public class BookingDTO {


    private Long Id;

    private Long salonId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Set<Long> serviceIds;

    private BookingStatus status = BookingStatus.PENDING;

    private int totalPrice;

    private Set<ServiceDTO> services;

    private UserDTO user;

    private SalonDTO salon;
}
