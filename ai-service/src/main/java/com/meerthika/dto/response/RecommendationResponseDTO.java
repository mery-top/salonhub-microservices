package com.meerthika.dto.response;

import com.meerthika.dto.HairAttributeDTO;
import com.meerthika.dto.HairstyleImageDTO;
import lombok.Data;

import java.util.List;

@Data
public class RecommendationResponseDTO {
    private HairAttributeDTO attributes;
    private List<HairstyleImageDTO> similarStyles;
    private String explanation;
}
