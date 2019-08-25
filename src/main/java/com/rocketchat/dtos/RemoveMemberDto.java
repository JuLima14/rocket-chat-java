package com.rocketchat.dtos;

import com.google.gson.JsonSyntaxException;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;

public class RemoveMemberDto extends DataTransferObjectType {
    private Chat chat;
    private User user;

    public RemoveMemberDto(Chat chat, User user, String type) {
        super(type);
        this.chat = chat;
        this.user = user;
    }

    @Override
    public void validate() throws JsonSyntaxException {
        if(!type.equals("remove_member")) {
            throw new JsonSyntaxException("!type.equals(\"remove_member\")");
        }
    }
}
