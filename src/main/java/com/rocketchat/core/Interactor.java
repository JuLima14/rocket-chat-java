package com.rocketchat.core;

import com.rocketchat.models.user.User;
import com.rocketchat.models.chat.Chat;

public interface Interactor {

    void registerUser(User user);
    void createChat(Chat chat);
}
