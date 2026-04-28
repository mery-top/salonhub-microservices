package com.meerthika.controller;

import com.meerthika.dto.HairAttributeDTO;
import com.meerthika.dto.response.RecommendationResponseDTO;
import com.meerthika.service.impl.EmbeddingService;
import com.meerthika.service.impl.ImageProcessingService;
import com.meerthika.service.impl.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/hair")
@RequiredArgsConstructor
public class HairController {

    private final RecommendationService recommendationService;
    private final ImageProcessingService imageProcessingService;

    @PostMapping("/analyze")
    public ResponseEntity<RecommendationResponseDTO> analyze(@RequestParam("file") MultipartFile file) {
        RecommendationResponseDTO response = recommendationService.processImage(file);
        return ResponseEntity.ok(response);
    }



}
