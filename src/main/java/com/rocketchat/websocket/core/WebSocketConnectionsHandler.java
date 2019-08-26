package com.rocketchat.websocket.core.providers;

import com.rocketchat.websocket.core.ConnectionsHandler;
import com.rocketchat.websocket.models.Connection;

import java.util.concurrent.ConcurrentHashMap;

public class WebSocketConnectionsHandler implements ConnectionsHandler {
    private ConcurrentHashMap<String, Connection> sessions;

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
}
