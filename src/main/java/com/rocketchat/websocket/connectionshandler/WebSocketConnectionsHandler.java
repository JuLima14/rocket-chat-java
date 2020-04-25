package com.rocketchat.websocket.connectionshandler;

import com.rocketchat.websocket.core.ConnectionsHandler;
import com.rocketchat.websocket.models.Connection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketConnectionsHandler implements ConnectionsHandler {
    private Map<String, Connection> sessions;

    public WebSocketConnectionsHandler() {
        this.sessions = new ConcurrentHashMap<>();
    }

    @Override
    public Connection get(String userId) {
        return sessions.get(userId);
    }

    @Override
    public void set(String key, Connection connection) {
        sessions.put(key, connection);
    }

    @Override
    public void remove(String key) {
        sessions.remove(key);
    }
}