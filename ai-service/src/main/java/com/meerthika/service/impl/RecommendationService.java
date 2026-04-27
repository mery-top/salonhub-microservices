//package com.meerthika.service.impl;
//
//
//import com.meerthika.dto.HairAttributeDTO;
//import com.meerthika.dto.HairstyleImageDTO;
//import com.meerthika.dto.response.RecommendationResponseDTO;
//import com.meerthika.service.EmbeddingGenerator;
//import com.meerthika.service.ExplanationGenerator;
//import com.meerthika.service.ImageProcessor;
//import com.meerthika.service.VectorSearcher;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class RecommendationService {
//    private final ImageProcessor imageService;
//    private final EmbeddingGenerator embeddingService;
//    private final VectorSearcher vectorService;
//    private final ExplanationGenerator ragService;
//
//    public RecommendationResponseDTO processImage(MultipartFile file){
//
//        HairAttributeDTO attributes = imageService.extractAttributes(file);
//
//        List<Float> embedding = embeddingService.generateEmbedding(file);
//
//        List<HairstyleImageDTO> styles = vectorService.findSimilar(embedding);
//
//        //need to send the context also here
//        String explanation = ragService.generateExplanation(
//                attributes.getFaceShape(),
//                attributes.getSkinTone()
//        );
//
//        RecommendationResponseDTO response = new RecommendationResponseDTO();
//        response.setAttributes(attributes);
//        response.setSimilarStyles(styles);
//        response.setExplanation(explanation);
//
//        return response;
//
//
//    }
//}
