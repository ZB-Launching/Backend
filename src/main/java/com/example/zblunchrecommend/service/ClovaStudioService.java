package com.example.zblunchrecommend.service;

import com.example.zblunchrecommend.dto.ChatCompletionsRequest;
import com.example.zblunchrecommend.dto.ChatMessage;
import com.example.zblunchrecommend.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClovaStudioService {
    @Value("${clova.id}")
    private String clientId;
    @Value("${clova.secret}")
    private String clientSecret;

    private String clovaApiUrl = "https://clovastudio.apigw.ntruss.com/v1/chat-completions/HCX-003";

    public String clovaMenuRecommend() {
        // 요청 헤더
        HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.set("X-NCP-CLOVASTUDIO-API-KEY", "");
        requestHeaders.set("X-NCP-APIGW-API-KEY", "");
        requestHeaders.set("Content-Type", "application/json");

        // Clova API에 전달할 메시지
        String systemMsg = "1. 사용자의 위치와 취향을 고려해서 점심 메뉴를 추천해야 합니다.";
        String userMsg = String.format("점심 메뉴를 추천해줘. 사용자의 선택: %s, 사용자의 취향: %s", "dd", "ddd"); // 추후 수정

        // 요청 객체
        ChatMessage systemChatMessage = new ChatMessage();
        systemChatMessage.setRole(Role.system);
        systemChatMessage.setContent(systemMsg);

        ChatMessage userChatMessage = new ChatMessage();
        userChatMessage.setRole(Role.user);
        userChatMessage.setContent(userMsg);

        List<ChatMessage> chatMessages = List.of(systemChatMessage, userChatMessage);

        ChatCompletionsRequest chatCompletionsRequest = new ChatCompletionsRequest();
        chatCompletionsRequest.setMessages(chatMessages);

        return null;
    }
}



