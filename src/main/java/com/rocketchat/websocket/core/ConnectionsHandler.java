package com.rocketchat.websocket.core;

import com.rocketchat.websocket.models.Connection;
import org.eclipse.jetty.websocket.api.Session;

public interface ConnectionsHandler {
    Connection get(String userId);
    void set(String key, Connection connection);
    void remove(String key);
}
