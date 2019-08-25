package com.rocketchat.websocket.core.interactors;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.core.providers.JSONInterpreter;


public class MessageInteractor implements Interactor {

    private Storage<User> userStorage;
    private Storage<Chat> chatStorage;
    private JSONInterpreter interpreter;

    public MessageInteractor(Storage<User> userStorage, Storage<Chat> chatStorage, JSONInterpreter interpreter) {
        this.userStorage = userStorage;
        this.chatStorage = chatStorage;
        this.interpreter = interpreter;
    }

    @Override
    public void process(byte[] data) {
        interpreter.process(data);
    }

    public Boolean registerUser(User user) {
        Boolean isValid = validateUser(user);
        if(isValid) {
            userStorage.set(user);
        }
        return isValid;
    }

    public Boolean createChat(Chat chat) {
        Boolean isValid = validateChat(chat);
        if(isValid) {
            chatStorage.set(chat);
        }
        return isValid;
    }

    private Boolean validateUser(User user) {
        return true;
    }

    private Boolean validateChat(Chat chat) {
        return true;
    }

}
