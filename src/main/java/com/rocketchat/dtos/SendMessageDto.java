package com.rocketchat.dtos;

import com.google.gson.JsonSyntaxException;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.message.Message;

public class SendMessageDto extends DataTransferObjectType {

    private Chat chat;
    private Message message;

    public SendMessageDto(String type, Chat chat, Message message) {
        super(type);
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

    @Override
    public SendMessageDto validate() throws JsonSyntaxException {
        if(!type.equals("send_message")) {
            throw new JsonSyntaxException("!type.equals(\"send_message\")");
        }
        return this;
    }
}
