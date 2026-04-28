package com.meerthika.service.impl;

import com.meerthika.service.ExplanationGenerator;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExplanationService implements ExplanationGenerator {

    private ChatClient chatClient;

    public ExplanationService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("""
                    You are an expert hairstylist and beauty advisor.
                    Suggest hairstyles and hair colors based on face shape and skin tone.
                    Be specific, practical, and trendy.
                """)
                .build();
    }

    public String generateExplanation(String faceShape, String skinTone) {

        String prompt = """
            Suggest hairstyles and hair colors.
            Face shape: %s
            Skin tone: %s
            """.formatted(faceShape, skinTone);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }


}
