package com.meerthika.controller;

import com.meerthika.dto.SalonDTO;
import com.meerthika.modal.Category;
import com.meerthika.service.CategoryService;
import com.meerthika.service.client.SalonFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/categories/salon-owner")
public class SalonCategoryController {

    private final CategoryService categoryService;
    private final SalonFeignClient salonFeignClient;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
    @RequestHeader("Authorization") String jwt) throws Exception {
        SalonDTO salonDTO = salonFeignClient.getSalonsByOwnerId(jwt).getBody();
        Category savedCategory = categoryService.saveCategory(category, salonDTO);

        return ResponseEntity.ok(savedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws Exception{
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        categoryService.deleteCategoryById(id, salonDTO.getId());


        return ResponseEntity.ok("category deleted sucesssfully");
    }

    @GetMapping("/salon/{salonId}/category/{id}")
    public ResponseEntity<Category> getCategoriesByIdAndSalon(@PathVariable Long id,
                                                                   @PathVariable Long salonId) throws Exception {
        Category category = categoryService.findByIdAndSalonId(id, salonId);
        return ResponseEntity.ok(category);
    }


}
