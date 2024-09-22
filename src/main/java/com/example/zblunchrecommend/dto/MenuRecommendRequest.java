package com.example.zblunchrecommend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuRecommendRequest {
    private String chooseType;
    private List<String> foodType;
    private List<String> foodDetail;
}
