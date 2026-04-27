//package com.meerthika.service.impl;
//
//import com.meerthika.service.ExplanationGenerator;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RagService implements ExplanationGenerator {
//
//    @Autowired
//    private ChatClient chatClient;
//
//    public String generateExplanation(String faceShape, String skinTone) {
//
//        //provide the vector store for sending the context to the prompt
//
//        String prompt = """
//            Suggest hairstyles and hair colors.
//            Face shape: %s
//            Skin tone: %s
//            """.formatted(faceShape, skinTone);
//
//        return chatClient.prompt()
//                .user(prompt)
//                .call()
//                .content();
//    }
//
//
//}
