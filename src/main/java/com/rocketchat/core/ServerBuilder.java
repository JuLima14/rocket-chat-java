package com.rocketchat.core;

import com.google.gson.Gson;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.InMemoryStorage;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.core.QueueExecutor;
import com.rocketchat.websocket.core.WebSocketHandler;
import com.rocketchat.websocket.core.producers.MessageProducer;
import com.rocketchat.websocket.core.producers.Producer;
import com.rocketchat.websocket.core.providers.*;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;
import static spark.Spark.init;

public class ServerBuilder {

    public ServerBuilder(TypeServer type) {

        setup();

        if(type == TypeServer.WEB_SOCKET) {
            setupWebSockets();
        } else if(type == TypeServer.REST_API) {
            setupRestApi();
        }
    }

    private void setup() {
        staticFileLocation("static");
        port(8080);
    }

    private void setupRestApi() {

    }

    private void setupWebSockets() {
        Producer producer = new MessageProducer();
        QueueExecutor executor = new QueueExecutor(producer);
        Gson gson = new Gson();

        Storage<User> userStorage = new InMemoryStorage<>();
        Storage<Chat> chatStorage = new InMemoryStorage<>();

        List<JSONInterpreter> interpreters = new ArrayList<>();
        interpreters.add(new RegisterUserInterpreter(gson, userStorage));
        interpreters.add(new AddMemberChatInterpreter(gson));
        interpreters.add(new CreateChatInterpreter(gson, chatStorage));
        interpreters.add(new DeleteChatInterpreter(gson));


        JSONInterpreter interpreter = new JSONInterpreterProcessor(interpreters);

        WebSocketHandler socket = new WebSocketHandler(executor, interpreter);

        webSocket("/socket", socket);
        init();
    }
}
