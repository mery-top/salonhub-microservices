package com.meerthika.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmbeddingGenerator {
    public List<Float> generateEmbedding(MultipartFile file);
}
