package com.meerthika.service;


import com.meerthika.dto.CategoryDTO;
import com.meerthika.dto.SalonDTO;
import com.meerthika.dto.ServiceDTO;
import com.meerthika.modal.ServiceOffering;
import jdk.jfr.Category;

public interface ServiceOfferingService {

    ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);
}
