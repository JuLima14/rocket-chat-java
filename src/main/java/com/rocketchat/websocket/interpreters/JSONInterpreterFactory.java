package com.rocketchat.websocket.interpreters;

import com.rocketchat.core.JsonDecoder;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;
import com.rocketchat.core.bigqueue.BigQueue;
import com.rocketchat.websocket.core.JSONInterpreter;
import com.rocketchat.websocket.core.Producer;

import java.util.ArrayList;
import java.util.List;

public class JSONInterpreterFactory {
    JsonDecoder jsonDecoder;
    Storage<User> userStorage;
    Storage<Chat> chatStorage;
    BigQueue bigQueue;
    Producer producer;

    public JSONInterpreterFactory(Storage<User> userStorage, Storage<Chat> chatStorage, JsonDecoder jsonDecoder, BigQueue bigQueue, Producer producer) {
        this.userStorage = userStorage;
        this.chatStorage = chatStorage;
        this.jsonDecoder = jsonDecoder;
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
        return new RegisterUserInterpreter(jsonDecoder, userStorage);
    }

    public JSONInterpreter getAddMemberChatInterpreter() {
        return new AddMemberChatInterpreter(jsonDecoder);
    }

    public JSONInterpreter getCreateChatInterpreter() {
        return new CreateChatInterpreter(jsonDecoder, chatStorage);
    }

    public JSONInterpreter getDeleteChatInterpreter() {
        return new DeleteChatInterpreter(jsonDecoder, chatStorage);
    }

    public JSONInterpreter getRemoveMemberInterpreter() {
        return new RemoveMemberInterpreter(jsonDecoder, chatStorage, bigQueue);
    }

    public JSONInterpreter getSendMessageInterpreter() {
        return new SendMessageInterpreter(jsonDecoder, producer, bigQueue);
    }
}
