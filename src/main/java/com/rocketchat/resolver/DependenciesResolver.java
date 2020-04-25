package com.rocketchat.resolver;

import com.rocketchat.core.GsonJsonDecoder;
import com.rocketchat.core.JsonDecoder;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.InMemoryStorage;
import com.rocketchat.storage.Storage;
import com.rocketchat.core.bigqueue.BigQueue;
import com.rocketchat.websocket.connectionshandler.WebSocketConnectionsHandler;
import com.rocketchat.websocket.handler.WebSocketHandler;
import com.rocketchat.websocket.interpreters.JSONInterpreterFactory;
import com.rocketchat.websocket.interpreters.JSONInterpreterProcessor;
import com.rocketchat.websocket.producers.MessageProducer;
import com.rocketchat.websocket.core.Producer;

public class DependenciesResolver {

    private static DependenciesResolver instance;

    BigQueue bigQueue;
    Producer producer;
    JsonDecoder jsonStrategy;
    Storage<User> userStorage;
    Storage<Chat> chatStorage;
    JSONInterpreterFactory jsonInterpreterFactory;
    JSONInterpreterProcessor jsonInterpreterProcessor;
    WebSocketConnectionsHandler webSocketConnectionsHandler;
    WebSocketHandler webSocketHandler;

    private DependenciesResolver() {
        producer = new MessageProducer();
        bigQueue = new BigQueue(producer);
        userStorage = new InMemoryStorage<>();
        chatStorage = new InMemoryStorage<>();
        jsonStrategy = new GsonJsonDecoder();
        jsonInterpreterFactory = new JSONInterpreterFactory(userStorage, chatStorage, jsonStrategy, bigQueue, producer);
        jsonInterpreterProcessor = new JSONInterpreterProcessor(jsonInterpreterFactory.getInterpreters());
        webSocketConnectionsHandler = new WebSocketConnectionsHandler();
        webSocketHandler = new WebSocketHandler(jsonInterpreterProcessor, webSocketConnectionsHandler);
    }

    public static DependenciesResolver getInstance() {
        if (instance == null) {
            instance = new DependenciesResolver();
        }
        return instance;
    }

    public BigQueue resolveBigQueue() {
        return bigQueue;
    }

    public Producer resolveProducer() {
        return producer;
    }

    public JsonDecoder resolveJSONStrategy() {
        return jsonStrategy;
    }

    public Storage<User> resolveUserStorage() {
        return userStorage;
    }

    public Storage<Chat> resolveChatStorage() {
        return chatStorage;
    }

    public JSONInterpreterFactory resolveJSONInterpreterFactory() {
        return jsonInterpreterFactory;
    }

    public JSONInterpreterProcessor resolveJSONInterpreterProcessor() {
        return jsonInterpreterProcessor;
    }

    public WebSocketConnectionsHandler resolveWebSocketConnectionsHandler() {
        return webSocketConnectionsHandler;
    }

    public WebSocketHandler resolveWebSocketHandler() {
        return webSocketHandler;
    }
}
