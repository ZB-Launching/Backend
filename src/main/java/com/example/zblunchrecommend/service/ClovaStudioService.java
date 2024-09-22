package com.example.zblunchrecommend.service;

import com.example.zblunchrecommend.dto.*;
import com.example.zblunchrecommend.enums.ChooseType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ClovaStudioService {
    public static final String X_NCP_CLOVASTUDIO_API_KEY = "X-NCP-CLOVASTUDIO-API-KEY";
    public static final String X_NCP_APIGW_API_KEY = "X-NCP-APIGW-API-KEY";
    @Value("${clova.key}")
    private String clientAPIKey;
    @Value("${clova.gateway}")
    private String clientGateway;
    private final WebClient webClient;

    public ClovaStudioService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://clovastudio.stream.ntruss.com").build();
    }

    public MenuRecommendResponse clovaMenuRecommend(MenuRecommendRequest menuRecommendRequest) {
        // Clova API에 전달할 메시지
        String userMsg = getUserMsg(menuRecommendRequest);

        // 요청 객체
        ChatMessage systemChatMessage = ChatMessage.createSystemMsg();
        ChatMessage userChatMessage = ChatMessage.createUserMsg(userMsg);

        List<ChatMessage> chatMessages = List.of(systemChatMessage, userChatMessage);

        ChatCompletionsRequest chatCompletionsRequest = ChatCompletionsRequest.create(chatMessages);

        //Clova API 통신
        ChatCompletionsResponse response = webClient.post()
                .uri("/testapp/v1/chat-completions/HCX-003")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(X_NCP_CLOVASTUDIO_API_KEY, clientAPIKey)
                .header(X_NCP_APIGW_API_KEY, clientGateway)
                .bodyValue(chatCompletionsRequest)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // 요청 전송
                .bodyToMono(ChatCompletionsResponse.class)
                .block();

        String menu = response.getResult().getMessage().getContent();
        return new MenuRecommendResponse(menu);
    }

    private String getUserMsg(MenuRecommendRequest menuRecommendRequest) {
        List<String> foodType = menuRecommendRequest.getFoodType();
        String foodList = String.join(", ", foodType);

        if (ChooseType.PREFERENCES.getValue().equals(menuRecommendRequest.getChooseType())) {
            return String.format(ChooseType.PREFERENCES.getContentFormat(),
                    menuRecommendRequest.getChooseType(),
                    foodList,
                    menuRecommendRequest.getFoodDetail());
        } else if (ChooseType.NOT_PREFERENCES.getValue().equals(menuRecommendRequest.getChooseType())) {
            return String.format(ChooseType.NOT_PREFERENCES.getContentFormat(),
                    menuRecommendRequest.getChooseType(),
                    foodList);
        } else {
            return String.format(ChooseType.RANDOM.getContentFormat(),
                    menuRecommendRequest.getChooseType());
        }
    }
}
