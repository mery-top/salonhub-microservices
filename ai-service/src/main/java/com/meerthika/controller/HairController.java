package com.meerthika.controller;

import com.meerthika.service.impl.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/hair")
@RequiredArgsConstructor
public class HairController {

    private final RecommendationService recommendationService;

    @PostMapping("/analyze")
    public Object analyze(@RequestParam("file") MultipartFile file) {
        return recommendationService.processImage(file);
    }

}
