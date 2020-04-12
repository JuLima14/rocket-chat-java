package com.rocketchat.websocket.core;

import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.models.Connection;

import java.util.Optional;
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

    @Override
    public void remove(String key) {
        sessions.remove(key);
    }
}