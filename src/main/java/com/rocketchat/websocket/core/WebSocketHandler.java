package com.rocketchat.websocket.core;

import com.rocketchat.websocket.interpreters.JSONInterpreter;
import com.rocketchat.websocket.models.Connection;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.UpgradeRequest;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;
import java.util.Optional;

@WebSocket
public class WebSocketHandler {

    private final JSONInterpreter interpreter;
    private final ConnectionsHandler connectionsHandler;

    private static final String USER_ID = "user_id";

    public WebSocketHandler(JSONInterpreter interpreter, ConnectionsHandler connectionsHandler) {
        this.interpreter = interpreter;
        this.connectionsHandler = connectionsHandler;
    }

    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        String userId = getHeader(USER_ID, session);
        if (userId.isEmpty()) {
            System.out.println("Fail to connect with: " + session.getRemoteAddress().getAddress());
            session.getRemote().sendString("Invalid connection");
            session.close();
        } else {
            System.out.println("Connect: " + session.getRemoteAddress().getAddress());
            connectionsHandler.set(userId, new Connection(session));
        }
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
        String userId = getHeader(USER_ID, session);
        if(!userId.isEmpty()) {
            connectionsHandler.remove(userId);
        }
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @OnWebSocketMessage
    public void onMessage(Session session, byte[] data, int offset, int length) {
        System.out.println("New Binary Message Received");
        String userId = getHeader(USER_ID, session);
        if(!userId.isEmpty()) {
            interpreter.process(data, connectionsHandler.get(userId));
        }
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        System.out.println("New Text Message Received");
        String userId = getHeader(USER_ID, session);
        if(!userId.isEmpty()) {
            interpreter.process(message.getBytes(), connectionsHandler.get(userId));
        }
    }

    private String getHeader(String key, Session session) {
        Optional<UpgradeRequest> upgradeRequest = Optional.of(session.getUpgradeRequest());

        if(upgradeRequest.isPresent()) {
            String userId = upgradeRequest.get().getHeader(key);
            if(userId == null) {
                return "user_id";  // TODO: mock remove for new String
            }
            return userId;
        }

        return "";
    }

}

