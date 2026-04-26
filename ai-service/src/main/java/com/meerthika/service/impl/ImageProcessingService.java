package com.meerthika.service.impl;

import com.meerthika.dto.HairAttributeDTO;
import com.meerthika.service.ImageProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageProcessingService implements ImageProcessor {

    public HairAttributeDTO extractAttributes(MultipartFile file){

        //Replace with opencv
        HairAttributeDTO hairAttributeDTO = new HairAttributeDTO();
        hairAttributeDTO.setFaceShape("oval");
        hairAttributeDTO.setSkinTone("warm");
        hairAttributeDTO.setHairType("wavy");

        return hairAttributeDTO;
    }
}
