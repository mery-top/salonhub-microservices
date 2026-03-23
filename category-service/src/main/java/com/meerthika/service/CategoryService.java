package com.meerthika.service;

import com.meerthika.dto.SalonDTO;
import com.meerthika.modal.Category;

import java.util.Set;

public interface CategoryService {

    Category saveCategory(Category category, SalonDTO salonDTO);
    Set<Category> getAllCategoriesBySalon(Long id);
    Category getCategoryById(Long id);
    void deleteCategoryById(Long id);
}
