package com.meerthika.service.impl;


import com.meerthika.service.EmbeddingGenerator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmbeddingService implements EmbeddingGenerator {

    private final WebClient webClient = WebClient.create("http://localhost:8001");

    public List<Float> generateEmbedding(MultipartFile file) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        try {
            Map response = webClient.post()
                    .uri("/embed")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            return (List<Float>) response.get("embedding");

        } catch (Exception e) {
            throw new RuntimeException("Embedding service failed: " + e.getMessage());
        }

    }
}
