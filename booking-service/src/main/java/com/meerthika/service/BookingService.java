package com.meerthika.service;

import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.ServiceDTO;
import com.meerthika.dto.UserDTO;
import com.meerthika.modal.Booking;

import java.util.Set;

public interface BookingService {


    Booking createBooking(BookingRequest booking,
                          UserDTO user, SalonDTO salon,
                          Set<ServiceDTO> serviceDTOSet
                          );
}
