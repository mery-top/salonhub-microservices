package com.meerthika.service;

import com.meerthika.dto.HairAttributeDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ImageProcessor {
    public HairAttributeDTO extractAttributes(MultipartFile file);
}
