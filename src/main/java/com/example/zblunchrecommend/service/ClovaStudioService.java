package com.example.zblunchrecommend.service;

import com.example.zblunchrecommend.dto.*;
import com.example.zblunchrecommend.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClovaStudioService {
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
        String systemMsg = "1. 답변은 1개만 제공해야 합니다.\n" +
                "2. 부가적인 설명은 생략하고 결과만 제공해야 합니다.\n" +
                "3. 명사형으로 제공해야 합니다.";
        String userMsg = getUserMsg(menuRecommendRequest);

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

        //Clova API 통신
        ChatCompletionsResponse response = webClient.post()
                .uri("/testapp/v1/chat-completions/HCX-003")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-NCP-CLOVASTUDIO-API-KEY", clientAPIKey)
                .header("X-NCP-APIGW-API-KEY", clientGateway)
                .bodyValue(chatCompletionsRequest)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // 요청 전송
                .bodyToMono(ChatCompletionsResponse.class)
                .block();

        String menu = response.getResult().getMessage().getContent();
        return new MenuRecommendResponse(menu);
    }

    private static String getUserMsg(MenuRecommendRequest menuRecommendRequest) {
        List<String> foodType = menuRecommendRequest.getFoodType();
        String foodList = String.join(", ", foodType);

        if(menuRecommendRequest.getChooseType().equals("선호")) {
            return String.format("점심 메뉴를 추천해줘. \n"
                            + "가격: 15,000원 이하 \n"
                            + "%s하는 음식: %s \n"
                            + "종류: %s 종류의 음식",
                    menuRecommendRequest.getChooseType(),
                    foodList,
                    menuRecommendRequest.getFoodDetail());
        } else if(menuRecommendRequest.getChooseType().equals("비선호")) { // foodType 중복 고려해서 다시 수정
            return String.format("점심 메뉴를 추천해줘. \n"
                            + "가격: 15,000원 이하 \n"
                            + "%s하는 음식: %s \n",
                    menuRecommendRequest.getChooseType(),
                    foodList);
        } else {
            return String.format("점심 메뉴를 추천해줘. \n"
                            + "가격: 15,000원 이하 \n"
                            + "%s한 음식",
                    menuRecommendRequest.getChooseType());
        }
    }
}



