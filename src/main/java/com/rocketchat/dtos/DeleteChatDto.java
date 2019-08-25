package com.rocketchat.dtos;

import com.google.gson.JsonSyntaxException;
import com.rocketchat.models.chat.Chat;

public class DeleteChatDto extends DataTransferObjectType {

    private Chat chat;

    public DeleteChatDto(String type, Chat chat) {
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
        if(!type.equals("delete_chat")) {
            throw new JsonSyntaxException("!type.equals(\"delete_chat\")");
        }
    }
}
