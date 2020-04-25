package com.rocketchat.dtos;

import com.google.gson.JsonSyntaxException;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.message.Message;

public class SendMessageDto {

    private Chat chat;
    private Message message;

    public SendMessageDto(Chat chat, Message message) {
        this.chat = chat;
        this.message = message;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
