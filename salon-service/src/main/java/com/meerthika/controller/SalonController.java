package com.meerthika.controller;

import com.meerthika.mapper.SalonMapper;
import com.meerthika.modal.Salon;
import com.meerthika.payload.dto.SalonDTO;
import com.meerthika.payload.dto.UserDTO;
import com.meerthika.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salon")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO){

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.createSalon(salonDTO, userDTO);

        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SalonDTO> updateSalon(@PathVariable("id") Long salonId, @RequestBody SalonDTO salonDTO) throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.updateSalon(salonDTO, userDTO, salonId);

        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    @GetMapping
    public ResponseEntity<List<SalonDTO>> getSalons() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        List<Salon> salons = salonService.getAllSalons();

        List<SalonDTO> salonDTOS = salons.stream().map((salon)->
                {
                    SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
                    return salonDTO;
                }
                ).toList();

        return ResponseEntity.ok(salonDTOS);
    }

}
