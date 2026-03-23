package com.meerthika.service.impl;

import com.meerthika.dto.SalonDTO;
import com.meerthika.modal.Category;
import com.meerthika.repository.CategoryRepository;
import com.meerthika.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category, SalonDTO salonDTO) {

        Category newCategory = new Category();

        newCategory.setName(category.getName());
        newCategory.setSalonId(salonDTO.getId());
        newCategory.setImage(category.getImage());
        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesBySalon(Long id) {
        return Set.of();
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }
}
