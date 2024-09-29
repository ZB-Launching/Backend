package com.example.zblunchrecommend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatCompletionsResponse {
    private ResponseStatus status;
    private ResponseResult result;
}
