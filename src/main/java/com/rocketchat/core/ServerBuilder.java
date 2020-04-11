package com.rocketchat.core;

import com.google.gson.Gson;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.InMemoryStorage;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.core.BigQueue;
import com.rocketchat.websocket.core.ConnectionsHandler;
import com.rocketchat.websocket.core.WebSocketConnectionsHandler;
import com.rocketchat.websocket.core.WebSocketHandler;
import com.rocketchat.websocket.interpreters.*;
import com.rocketchat.websocket.producers.MessageProducer;
import com.rocketchat.websocket.producers.Producer;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

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
        // TODO
        Storage<User> userStorage = new InMemoryStorage<>();
        Storage<Chat> chatStorage = new InMemoryStorage<>();

        Producer producer = new MessageProducer();
        BigQueue bigQueue = new BigQueue(producer);


    }

    private void setupWebSockets() {
        // TODO: Add repository layer to avoid making business logic in every Interpreter.
        Storage<User> userStorage = new InMemoryStorage<>();
        Storage<Chat> chatStorage = new InMemoryStorage<>();

        Producer producer = new MessageProducer();
        BigQueue bigQueue = new BigQueue(producer);

        Gson gson = new Gson();

        List<JSONInterpreter> interpreters = new ArrayList<>();
        interpreters.add(new RegisterUserInterpreter(gson, userStorage));
        interpreters.add(new AddMemberChatInterpreter(gson));
        interpreters.add(new CreateChatInterpreter(gson, chatStorage));
        interpreters.add(new DeleteChatInterpreter(gson, chatStorage));
        interpreters.add(new RemoveMemberInterpreter(gson, chatStorage, bigQueue));
        interpreters.add(new SendMessageInterpreter(gson, producer, bigQueue));

        JSONInterpreter interpreter = new JSONInterpreterProcessor(interpreters);

        // Connections handlers
        ConnectionsHandler connectionsHandler = new WebSocketConnectionsHandler();
        WebSocketHandler socket = new WebSocketHandler(interpreter, connectionsHandler);

        webSocket("/socket", socket);

        init();
    }
}
