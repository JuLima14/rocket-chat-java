package com.rocketchat.dtos;

import com.google.gson.JsonSyntaxException;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;

public class AddMemberDto extends DataTransferObjectType {
    private Chat chat;
    private User user;

    public AddMemberDto(Chat chat, User user, String type) {
        super(type);
        this.chat = chat;
        this.user = user;
    }

    @Override
    public void validate() throws JsonSyntaxException {
        if(!type.equals("add_member")) {
            throw new JsonSyntaxException("!type.equals(\"add_member\")");
        }
    }
}
