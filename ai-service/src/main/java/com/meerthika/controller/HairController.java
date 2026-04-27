package com.meerthika.controller;

import com.meerthika.dto.HairAttributeDTO;
import com.meerthika.service.impl.EmbeddingService;
import com.meerthika.service.impl.ImageProcessingService;
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

//    private final RecommendationService recommendationService;
    private final ImageProcessingService imageProcessingService;
    private final EmbeddingService embeddingService;

//    @PostMapping("/analyze")
//    public Object analyze(@RequestParam("file") MultipartFile file) {
//        return recommendationService.processImage(file);
//    }

    @PostMapping("/check")
    public ResponseEntity<List<Float>> image(@RequestParam("file") MultipartFile file){
        List<Float> embed =  embeddingService.generateEmbedding(file);
        return ResponseEntity.ok(embed);
    }

}
