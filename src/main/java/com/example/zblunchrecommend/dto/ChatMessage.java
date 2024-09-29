package com.example.zblunchrecommend.dto;

import com.example.zblunchrecommend.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private Role role;
    private String content;

    public static ChatMessage createSystemMsg() {
        ChatMessage chatMsg = new ChatMessage();
        chatMsg.setRole(Role.system);
        chatMsg.setContent("1. 답변은 1개만 제공해야 합니다.\n" +
                "2. 부가적인 설명은 생략하고 결과만 제공해야 합니다.\n" +
                "3. 명사형으로 제공해야 합니다.");
        return chatMsg;
    }

    public static ChatMessage createUserMsg(String userMsg) {
        ChatMessage chatMsg = new ChatMessage();
        chatMsg.setRole(Role.user);
        chatMsg.setContent(userMsg);
        return chatMsg;
    }
}
