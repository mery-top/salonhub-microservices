package com.meerthika.controller;


import com.meerthika.dto.CategoryDTO;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.ServiceDTO;
import com.meerthika.modal.ServiceOffering;
import com.meerthika.service.ServiceOfferingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {


    private ServiceOfferingService serviceOfferingService;

    public ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO) {

        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setImage(serviceDTO.getImage());
        serviceOffering.setSalonId(salonDTO.getId());
        serviceOffering.setName(serviceDTO.getName());
        serviceOffering.setDescription(serviceDTO.getDescription());
        serviceOffering.setCategoryId(categoryDTO.getId());
        serviceOffering.setPrice(serviceDTO.getPrice());
        serviceOffering.setDuration(serviceDTO.getDuration());

        return serviceOfferingRepository.save(serviceOffering);
    }

    public ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception {

        ServiceOffering serviceOffering = serviceOfferingRepository.findById(serviceId).orElse(null);

        if(serviceOffering == null){
            throw new Exception("service not exist with id" + serviceId);
        }

        serviceOffering.setImage(service.getImage());
        serviceOffering.setName(service.getName());
        serviceOffering.setDescription(service.getDescription());
        serviceOffering.setPrice(service.getPrice());
        serviceOffering.setDuration(service.getDuration());

        return serviceOfferingRepository.save(serviceOffering);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getAllServicesBySalonId(@PathVariable Long salonId, @RequestParam(required = false) Long categoryId) {

        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getAllServicesBySalonId(salonId, categoryId);

        return ResponseEntity.ok(serviceOfferings);
    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(@PathVariable Set<Long> ids) {

        Set<ServiceOffering> services = serviceOfferingService.getServicesByIds(ids);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable Long id) throws Exception {

        ServiceOffering serviceOffering = serviceOfferingService.getServiceById(id);
        return ResponseEntity.ok(serviceOffering);
    }
}
