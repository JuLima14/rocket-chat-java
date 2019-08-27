package com.rocketchat.websocket.core;

import com.rocketchat.websocket.core.interpreters.JSONInterpreter;
import com.rocketchat.websocket.models.Connection;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Optional;

@WebSocket
public class WebSocketHandler {

    private final JSONInterpreter interpreter;
    private final ConnectionsHandler connectionsHandler;

    public WebSocketHandler(JSONInterpreter interpreter, ConnectionsHandler connectionsHandler) {
        this.interpreter = interpreter;
        this.connectionsHandler = connectionsHandler;
    }

    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        Optional<String> userId = Optional.of(session.getUpgradeRequest().getHeader("user_id"));

        if (userId.orElse("").isEmpty()) {
            System.out.println("Fail to connect with: " + session.getRemoteAddress().getAddress());
            session.getRemote().sendString("invalid connection");
            session.close();
        } else {
            System.out.println("Connect: " + session.getRemoteAddress().getAddress());
            connectionsHandler.set(userId.get(), new Connection(session));
        }
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
        connectionsHandler.remove(session.getUpgradeRequest().getHeader("user_id"));
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @OnWebSocketMessage
    public void onMessage(Session session, byte[] data, int offset, int length) {
        System.out.println("New Binary Message Received");
        String userId = session.getUpgradeRequest().getHeader("user_id");
        if(!userId.isEmpty()) {
            interpreter.process(data, connectionsHandler.get(userId));
        }
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        System.out.println("New Text Message Received");
        String userId = session.getUpgradeRequest().getHeader("user_id");
        if(!userId.isEmpty()) {
            interpreter.process(message.getBytes(), connectionsHandler.get(userId));
        }
    }

}

