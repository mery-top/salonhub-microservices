package com.meerthika.controller;


import com.meerthika.domain.BookingStatus;
import com.meerthika.domain.PaymentMethod;
import com.meerthika.dto.*;
import com.meerthika.mapper.BookingMapper;
import com.meerthika.modal.Booking;
import com.meerthika.modal.SalonReport;
import com.meerthika.service.BookingService;
import com.meerthika.service.client.PaymentFeignClient;
import com.meerthika.service.client.SalonFeignClient;
import com.meerthika.service.client.ServiceOfferingFeignClient;
import com.meerthika.service.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final SalonFeignClient salonFeignClient;
    private final UserFeignClient userFeignClient;
    private final ServiceOfferingFeignClient serviceOfferingFeignClient;
    private final PaymentFeignClient paymentFeignClient;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam Long salonId,
                                                 @RequestBody BookingRequest bookingRequest,
                                                 @RequestParam PaymentMethod paymentMethod,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();
        SalonDTO salon = salonFeignClient.getSalonsById(salonId).getBody();

        Set<ServiceDTO> serviceDTOSet  = serviceOfferingFeignClient.getServicesByIds(bookingRequest.getServiceIds()).getBody();
        Booking booking = bookingService.createBooking(bookingRequest, user, salon, serviceDTOSet);

        BookingDTO bookingDTO = BookingMapper.toDTO(booking);
        paymentFeignClient.createPaymentLink(bookingDTO, paymentMethod);

        return ResponseEntity.ok(booking);

    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingsByCustomer(
    @RequestHeader("Authorization") String jwt
    ) throws Exception {
        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();
        if(user == null || user.getId() == null){
            throw new Exception("user not found from jwt");
        }
        List<Booking> bookings = bookingService.getBookingsByCustomer(user.getId());
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }



    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingsBySalon(

    ){
        List<Booking> bookings = bookingService.getBookingsBySalon(1L);
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }



    private Set<BookingDTO> getBookingDTOs(List<Booking> bookings){
        return bookings.stream()
                .map(booking -> {
                    return BookingMapper.toDTO(booking);
                }).collect(Collectors.toSet());
    }


    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(
        @PathVariable Long bookingId
    ) throws Exception {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam BookingStatus status
    ) throws Exception {
        Booking booking = bookingService.updateBooking(bookingId, status);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }


    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookedSlot(
            @PathVariable Long salonId,
            @RequestParam(required = false) LocalDate date
            ) throws Exception {
        List<Booking> bookings = bookingService.getBookingsByDate(date, salonId);

        List<BookingSlotDTO> slotDTOS = bookings.stream()
                .map(booking -> {
                    BookingSlotDTO slotDTO = new BookingSlotDTO();
                    slotDTO.setStartTime(booking.getStartTime());
                    slotDTO.setEndTime(booking.getEndTime());
                    return slotDTO;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(slotDTOS);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport(

    ) throws Exception {
        SalonReport salonReport= bookingService.getSalonReport(1L);

        return ResponseEntity.ok(salonReport);
    }


}
