package com.meerthika.service;

import com.meerthika.dto.SalonDTO;
import com.meerthika.modal.Category;

import java.util.Set;

public interface CategoryService {

    Category saveCategory(Category category, SalonDTO salonDTO);
    Set<Category> getAllCategoriesBySalon(Long id);
    Category getCategoryById(Long id) throws Exception;
    void deleteCategoryById(Long id, Long salonId) throws Exception;
    Category findByIdAndSalonId(Long id, Long SalonId) throws Exception;
}
