package com.meerthika.repository;

import com.meerthika.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findBySalonId(Long SalonId);


}
