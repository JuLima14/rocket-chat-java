package com.rocketchat.core;

import com.rocketchat.resolver.DependenciesResolver;
import com.rocketchat.websocket.core.WebSocketHandler;

import static spark.Spark.webSocket;

final public class WebSocketServer implements ServerBuilder {

    @Override
    public void build() {
        final DependenciesResolver resolver = DependenciesResolver.getInstance();
        final WebSocketHandler socket = resolver.resolveWebSocketHandler();

        webSocket("/socket", socket);
    }
}
