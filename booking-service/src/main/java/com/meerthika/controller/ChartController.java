package com.meerthika.controller;


import com.meerthika.dto.SalonDTO;
import com.meerthika.modal.Booking;
import com.meerthika.service.BookingService;
import com.meerthika.service.client.SalonFeignClient;
import com.meerthika.service.impl.BookingChartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings/chart")
@RequiredArgsConstructor
public class ChartController {

    private final BookingChartServiceImpl bookingChartServiceImpl;
    private final BookingService bookingService;
    private final SalonFeignClient salonService;

    @GetMapping("/earnings")
    public ResponseEntity<List<Map<String, Object>>> getEarningsChartData(
            @RequestHeader("Authorization") String jwt) throws Exception {

//        UserDTO user = userService.getUserFromJwtToken(jwt).getBody();

        SalonDTO salon = salonService.getSalonsByOwnerId(jwt).getBody();
        List<Booking> bookings=bookingService.getBookingsBySalon(salon.getId());

        // Generate chart data
        List<Map<String, Object>> chartData = bookingChartServiceImpl
                .generateEarningsChartData(bookings);

        return ResponseEntity.ok(chartData);

    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Map<String, Object>>> getBookingsChartData(
            @RequestHeader("Authorization") String jwt) throws Exception {

        SalonDTO salon = salonService.getSalonsByOwnerId(jwt).getBody();
        List<Booking> bookings=bookingService.getBookingsBySalon(salon.getId());
        // Generate chart data
        List<Map<String, Object>> chartData = bookingChartServiceImpl.generateBookingCountChartData(bookings);

        return ResponseEntity.ok(chartData);

    }
}
