package com.example.zblunchrecommend.dto;

import com.example.zblunchrecommend.enums.ChooseType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuRecommendRequest {
    private String chooseType = ChooseType.RANDOM.getValue();
    private List<String> foodType;
    private List<String> foodDetail;
}
