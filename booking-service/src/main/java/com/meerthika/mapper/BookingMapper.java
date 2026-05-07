package com.meerthika.mapper;

import com.meerthika.dto.BookingDTO;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.ServiceDTO;
import com.meerthika.dto.UserDTO;
import com.meerthika.modal.Booking;

import java.util.List;
import java.util.Set;

public class BookingMapper {


    public static BookingDTO toDTO(Booking booking, Set<ServiceDTO> services, SalonDTO salon, UserDTO user){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setSalonId(booking.getSalonId());
        bookingDTO.setServiceIds(booking.getServiceIds());
        bookingDTO.setTotalPrice(booking.getTotalPrice());
        bookingDTO.setServices(services);
        bookingDTO.setUser(user);
        bookingDTO.setSalon(salon);

        return bookingDTO;
    }
}
