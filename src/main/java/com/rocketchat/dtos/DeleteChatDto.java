package com.rocketchat.dtos;

import com.google.gson.JsonSyntaxException;
import com.rocketchat.models.chat.Chat;

public class DeleteChatDto {

    private Chat chat;

    public DeleteChatDto(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
