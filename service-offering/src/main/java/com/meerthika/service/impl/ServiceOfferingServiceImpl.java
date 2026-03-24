package com.meerthika.service.impl;

import com.meerthika.dto.CategoryDTO;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.ServiceDTO;
import com.meerthika.modal.ServiceOffering;
import com.meerthika.repository.ServiceOfferingRepository;
import com.meerthika.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {


    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) {
        return null;
    }

    @Override
    public Set<ServiceOffering> getAllServicesBySalonId(Long salonId, Long categoryId) {
        return Set.of();
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {
        return Set.of();
    }
}
