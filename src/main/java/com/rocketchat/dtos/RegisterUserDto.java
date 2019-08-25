package com.rocketchat.dtos;

import com.google.gson.JsonSyntaxException;
import com.rocketchat.models.message.Message;
import com.rocketchat.models.user.User;

public class RegisterUserDto extends DataTransferObjectType {

    private User user;

    public RegisterUserDto(String type, User user) {
        super(type);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void validate() throws JsonSyntaxException {
        if(!type.equals("register_user")) {
            throw new JsonSyntaxException("!type.equals(\"register_user\")");
        }
    }
}
