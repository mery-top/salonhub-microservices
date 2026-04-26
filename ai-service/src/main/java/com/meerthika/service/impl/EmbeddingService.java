package com.meerthika.service.impl;


import com.meerthika.service.EmbeddingGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmbeddingService implements EmbeddingGenerator {
    public List<Float> generateEmbedding(MultipartFile file) {

        // TODO: call Python CLIP API
        List<Float> vector = new ArrayList<>();

        for (int i = 0; i < 512; i++) {
            vector.add((float) Math.random());
        }

        return vector;
    }
}
