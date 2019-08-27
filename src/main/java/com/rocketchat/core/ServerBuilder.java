package com.rocketchat.core;

import com.google.gson.Gson;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.InMemoryStorage;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.core.ConnectionsHandler;
import com.rocketchat.websocket.core.QueueExecutor;
import com.rocketchat.websocket.core.WebSocketConnectionsHandler;
import com.rocketchat.websocket.core.WebSocketHandler;
import com.rocketchat.websocket.core.producers.MessageProducer;
import com.rocketchat.websocket.core.producers.Producer;
import com.rocketchat.websocket.core.interpreters.*;

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
        // TODO: Add repository layer to avoid making business logic in every Interpreter.
        // TODO: Start using the QueueExecutor through Constructor parameters to avoid statics properties/functions.
        Storage<User> userStorage = new InMemoryStorage<>();
        Storage<Chat> chatStorage = new InMemoryStorage<>();

        Producer producer = new MessageProducer();
        QueueExecutor executor = new QueueExecutor(producer);
        ConnectionsHandler connectionsHandler = new WebSocketConnectionsHandler();
        Gson gson = new Gson();

        List<JSONInterpreter> interpreters = new ArrayList<>();
        interpreters.add(new RegisterUserInterpreter(gson, userStorage));
        interpreters.add(new AddMemberChatInterpreter(gson));
        interpreters.add(new CreateChatInterpreter(gson, chatStorage));
        interpreters.add(new DeleteChatInterpreter(gson, chatStorage));
        interpreters.add(new RemoveMemberInterpreter(gson, chatStorage));
        interpreters.add(new SendMessageInterpreter(gson, producer));

        JSONInterpreter interpreter = new JSONInterpreterProcessor(interpreters);

        WebSocketHandler socket = new WebSocketHandler(interpreter, connectionsHandler);

        webSocket("/socket", socket);

        init();
    }
}
