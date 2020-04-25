package com.rocketchat.dtos;

import com.rocketchat.models.chat.Chat;

public class CreateChatDto {

    private Chat chat;

    public CreateChatDto(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
