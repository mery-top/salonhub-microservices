package com.meerthika.service.impl;

import com.meerthika.modal.Salon;
import com.meerthika.payload.dto.SalonDTO;
import com.meerthika.payload.dto.UserDTO;
import com.meerthika.repository.SalonRepository;
import com.meerthika.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {


    public final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {

        Salon salon = new Salon();
        salon.setName(req.getName());
        salon.setAddress(req.getAddress());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setImages(req.getImages());
        salon.setOwnerId(user.getId());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());
        salon.setPhoneNumber(req.getPhoneNumber());

        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception{

        Salon exisitingSalon = salonRepository.findById(salonId).orElse(null);
        if(exisitingSalon !=null && salon.getOwnerId().equals(user.getId())){
            exisitingSalon.setCity(salon.getCity());
            exisitingSalon.setName(salon.getName());
            exisitingSalon.setAddress(salon.getAddress());
            exisitingSalon.setEmail(salon.getEmail());
            exisitingSalon.setImages(salon.getImages());
            exisitingSalon.setOpenTime(salon.getOpenTime());
            exisitingSalon.setCloseTime(salon.getCloseTime());
            exisitingSalon.setPhoneNumber(salon.getPhoneNumber());
            exisitingSalon.setOwnerId(user.getId());
        }

        throw new Exception("salon not exist");
    }

    @Override
    public List<Salon> getAllSalons() {

        return List.of();
    }

    @Override
    public Salon getSalonById(Long salonId) {

        return null;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {

        return null;
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {

        return List.of();
    }
}
