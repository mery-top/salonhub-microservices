package com.meerthika.service;

import com.meerthika.modal.Salon;
import com.meerthika.payload.dto.SalonDTO;
import com.meerthika.payload.dto.UserDTO;

public interface SalonService {
    Salon createSalon(SalonDTO salon, UserDTO user);
}
