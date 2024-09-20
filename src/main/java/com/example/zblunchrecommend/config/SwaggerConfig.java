package com.example.zblunchrecommend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info().title("점심 메뉴 추천 Swagger")
                        .description("점심 메뉴 추천 API 명세")
                        .version("0.1"));
    }
}
