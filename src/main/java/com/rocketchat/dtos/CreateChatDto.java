package com.rocketchat.dtos;

import com.google.gson.JsonSyntaxException;
import com.rocketchat.models.chat.Chat;

public class CreateChatDto extends DataTransferObjectType {

    private Chat chat;

    public CreateChatDto(String type, Chat chat) {
        super(type);
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void validate() throws JsonSyntaxException {
        if(!type.equals("create_chat")) {
            throw new JsonSyntaxException("!type.equals(\"create_chat\")");
        }
    }
}
