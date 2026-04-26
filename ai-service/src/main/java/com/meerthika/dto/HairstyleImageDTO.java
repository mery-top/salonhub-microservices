package com.meerthika.dto;

import lombok.Data;

import java.util.List;

@Data
public class HairstyleImageDTO {
    private String imageUrl;
    private String style;
    private String color;
    private List<Float> embedding;
}
