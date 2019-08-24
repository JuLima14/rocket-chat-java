package com.rocketchat.core;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;

public class MessageInteractor implements Interactor {

    Storage<User> userStorage;
    Storage<Chat> chatStorage;

    public MessageInteractor(Storage<User> userStorage, Storage<Chat> chatStorage) {
        this.userStorage = userStorage;
        this.chatStorage = chatStorage;
    }

    @Override
    public void registerUser(User user) {
        if(validateUser(user)) {
            userStorage.set(user);
        }
    }

    @Override
    public void createChat(Chat chat) {

        if(validateChat(chat)) {
            chatStorage.set(chat);
        }

    }

    private Boolean validateUser(User user) {
        return true;
    }

    private Boolean validateChat(Chat chat) {
        return true;
    }
}
