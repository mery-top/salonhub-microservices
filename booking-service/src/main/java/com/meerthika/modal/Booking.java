package com.meerthika.modal;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Long salonId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    //it creates separate table for service ids
    @ElementCollection
    private Set<Long> serviceIds;

    private BookingStatus status;

    private int totalServices;
    //based on count of serviceIds;
}
