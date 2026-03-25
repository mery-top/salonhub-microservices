package com.meerthika.controller;


import com.meerthika.dto.CategoryDTO;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.ServiceDTO;
import com.meerthika.modal.ServiceOffering;
import com.meerthika.repository.ServiceOfferingRepository;
import com.meerthika.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;
    private final ServiceOfferingRepository serviceOfferingRepository;


    @PostMapping
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDTO serviceDTO) {

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategoryId());

       ServiceOffering serviceOfferings = serviceOfferingService.createService(salonDTO, serviceDTO, categoryDTO);


        return ResponseEntity.ok(serviceOfferings);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable Long serviceId, @RequestBody ServiceOffering service) throws Exception {

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);

        ServiceOffering serviceOfferings = serviceOfferingService.updateService(serviceId, service);

        return ResponseEntity.ok(serviceOfferings);
    }


}
