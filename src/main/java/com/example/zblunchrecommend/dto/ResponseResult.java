package com.example.zblunchrecommend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseResult {
    private ChatMessage message;
    private String stopReason;
    private Integer inputLength;
    private Integer outputLength;
    private List<AIFilter> aiFilter;
}
