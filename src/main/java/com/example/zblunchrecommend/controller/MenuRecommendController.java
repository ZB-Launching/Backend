package com.example.zblunchrecommend.controller;

import com.example.zblunchrecommend.service.ClovaStudioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "MenuRecommendController", description = "메뉴 추천 API")
public class MenuRecommendController {
    private final ClovaStudioService clovaStudioService;

    @PostMapping("/menuRecommend")
    @Operation(summary = "메뉴 추천 API")
    public String menuRecommend() {
        return clovaStudioService.clovaMenuRecommend();
    }
}
