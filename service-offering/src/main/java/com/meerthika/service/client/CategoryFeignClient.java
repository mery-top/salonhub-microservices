package com.meerthika.service.client;


import com.meerthika.dto.CategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CATEGORY-SERVICE")
public interface CategoryFeignClient {

    @GetMapping("/api/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id)
            throws Exception;

}
