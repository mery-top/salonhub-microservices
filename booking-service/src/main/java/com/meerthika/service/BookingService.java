package com.meerthika.service;

import com.meerthika.domain.BookingStatus;
import com.meerthika.dto.BookingRequest;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.ServiceDTO;
import com.meerthika.dto.UserDTO;
import com.meerthika.modal.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {


    Booking createBooking(BookingRequest booking,
                          UserDTO user, SalonDTO salon,
                          Set<ServiceDTO> serviceDTOSet
                          );

    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);

    Booking getBookingById(Long id);
    Booking updateBooking(Long bookingId, BookingStatus status);
    List<Booking> getBookingsByDate(LocalDate date, Long salonId);
}
