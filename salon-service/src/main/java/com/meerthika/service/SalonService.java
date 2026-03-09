package com.meerthika.service;

import com.meerthika.payload.dto.SalonDTO;

public interface SalonService {
    Salon createSalon(SalonDTO salon, UserDTO user);
}
