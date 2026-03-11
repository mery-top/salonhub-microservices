package com.meerthika.controller;

import com.meerthika.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salon")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;


}
