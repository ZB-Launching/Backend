package com.example.zblunchrecommend.dto;

import com.example.zblunchrecommend.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private Role role;
    private String content;
}
