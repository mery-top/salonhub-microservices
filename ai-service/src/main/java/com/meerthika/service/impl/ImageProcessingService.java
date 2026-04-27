package com.meerthika.service.impl;

import com.meerthika.dto.HairAttributeDTO;
import com.meerthika.service.ImageProcessor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ImageProcessingService implements ImageProcessor {

    private final WebClient webClient = WebClient.create("http://localhost:8000");

    public HairAttributeDTO extractAttributes(MultipartFile file){

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        try {
            return webClient.post()
                    .uri("/analyze")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(HairAttributeDTO.class)
                    .block(); // keep blocking for now (Spring MVC)

        } catch (Exception e) {
            throw new RuntimeException("Error calling ML service: " + e.getMessage());
        }

    }
}
