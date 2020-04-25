package com.rocketchat.dtos;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;

public class AddMemberDto {
    private Chat chat;
    private User user;

    public AddMemberDto(Chat chat, User user) {
        this.chat = chat;
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
