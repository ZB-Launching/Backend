package com.example.zblunchrecommend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatCompletionsRequest {
    private List<ChatMessage> messages;
    private Double temperature = 0.5;
    private Integer topK = 0;
    private Double topP = 0.8;
    private Double repeatPenalty = 5.0;
    private String[] stopBefore = new String[]{};
    private Integer maxTokens = 2000;
    private Boolean includeAiFilters = false;
    private Integer seed = 0;
}
