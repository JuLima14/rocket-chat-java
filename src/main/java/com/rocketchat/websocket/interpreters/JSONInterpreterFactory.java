package com.rocketchat.websocket.interpreters;

import com.google.gson.Gson;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.core.BigQueue;
import com.rocketchat.websocket.producers.Producer;

import java.util.ArrayList;
import java.util.List;

public class JSONInterpreterFactory {
    Gson gson;
    Storage<User> userStorage;
    Storage<Chat> chatStorage;
    BigQueue bigQueue;
    Producer producer;

    public JSONInterpreterFactory(Storage<User> userStorage, Storage<Chat> chatStorage, Gson gson, BigQueue bigQueue, Producer producer) {
        this.userStorage = userStorage;
        this.chatStorage = chatStorage;
        this.gson = gson;
        this.bigQueue = bigQueue;
        this.producer = producer;
    }

    public List<JSONInterpreter> getInterpreters() {
        final List<JSONInterpreter> interpreters = new ArrayList<>();

        interpreters.add(getRegisterUserInterpreter());
        interpreters.add(getAddMemberChatInterpreter());
        interpreters.add(getCreateChatInterpreter());
        interpreters.add(getDeleteChatInterpreter());
        interpreters.add(getRemoveMemberInterpreter());
        interpreters.add(getSendMessageInterpreter());

        return interpreters;
    }

    public JSONInterpreter getRegisterUserInterpreter() {
        return new RegisterUserInterpreter(gson, userStorage);
    }

    public JSONInterpreter getAddMemberChatInterpreter() {
        return new AddMemberChatInterpreter(gson);
    }

    public JSONInterpreter getCreateChatInterpreter() {
        return new CreateChatInterpreter(gson, chatStorage);
    }

    public JSONInterpreter getDeleteChatInterpreter() {
        return new DeleteChatInterpreter(gson, chatStorage);
    }

    public JSONInterpreter getRemoveMemberInterpreter() {
        return new RemoveMemberInterpreter(gson, chatStorage, bigQueue);
    }

    public JSONInterpreter getSendMessageInterpreter() {
        return new SendMessageInterpreter(gson, producer, bigQueue);
    }
}
