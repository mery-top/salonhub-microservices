package com.meerthika.service;


import com.meerthika.dto.CategoryDTO;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.ServiceDTO;
import com.meerthika.modal.ServiceOffering;
import jdk.jfr.Category;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);
    ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception;
    Set<ServiceOffering> getAllServicesBySalonId(Long salonId, Long categoryId);
    Set<ServiceOffering> getServicesByIds(Set<Long> ids);
    ServiceOffering getServiceById(Long id) throws Exception;


}
