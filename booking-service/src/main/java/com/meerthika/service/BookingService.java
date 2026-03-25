package com.meerthika.service;

import com.meerthika.domain.BookingStatus;
import com.meerthika.dto.BookingRequest;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.ServiceDTO;
import com.meerthika.dto.UserDTO;
import com.meerthika.modal.Booking;
import com.meerthika.modal.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {


    Booking createBooking(BookingRequest booking,
                          UserDTO user, SalonDTO salon,
                          Set<ServiceDTO> serviceDTOSet
                          ) throws Exception;

    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);

    Booking getBookingById(Long id) throws Exception;
    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingsByDate(LocalDate date, Long salonId);

    SalonReport getSalonReport(Long salonId);
}
