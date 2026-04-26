package com.meerthika.service.impl;

import com.meerthika.dto.HairstyleImageDTO;
import com.meerthika.service.VectorSearcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VectorSearchService implements VectorSearcher {

    public List<HairstyleImageDTO> findSimilar(List<Float> embedding) {

        // TODO: connect to FAISS / Pinecone
        List<HairstyleImageDTO> results = new ArrayList<>();

        HairstyleImageDTO img = new HairstyleImageDTO();
        img.setImageUrl("https://example.com/style1.jpg");
        img.setStyle("Bob Cut");
        img.setColor("Brown");

        results.add(img);

        return results;
    }
}
