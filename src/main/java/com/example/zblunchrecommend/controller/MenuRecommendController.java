package com.example.zblunchrecommend.controller;

import com.example.zblunchrecommend.dto.MenuRecommendResponse;
import com.example.zblunchrecommend.dto.MenuRecommendRequest;
import com.example.zblunchrecommend.service.ClovaStudioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "MenuRecommendController", description = "메뉴 추천 API")
public class MenuRecommendController {
    private final ClovaStudioService clovaStudioService;

    @PostMapping("/menuRecommend")
    @Operation(summary = "메뉴 추천 API")
    @ResponseBody
    public MenuRecommendResponse menuRecommend(MenuRecommendRequest menuRecommendRequest) {
//        menuRecommendRequest.setChooseType("비선호");
//        menuRecommendRequest.setFoodType(List.of("간단한 음식", "면", "국"));
//        menuRecommendRequest.setFoodDetail(List.of("빵"));
        return clovaStudioService.clovaMenuRecommend(menuRecommendRequest);
    }
}
