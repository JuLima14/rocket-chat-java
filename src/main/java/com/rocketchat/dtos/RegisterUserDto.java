package com.rocketchat.dtos;

import com.google.gson.JsonSyntaxException;
import com.rocketchat.models.user.User;

public class RegisterUserDto {

    private User user;

    public RegisterUserDto(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
