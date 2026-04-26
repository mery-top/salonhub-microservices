package com.meerthika.service;

import com.meerthika.dto.HairstyleImageDTO;

import java.util.List;

public interface VectorSearcher {

    public List<HairstyleImageDTO> findSimilar(List<Float> embedding);
}
