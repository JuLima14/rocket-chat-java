package com.rocketchat.websocket.core.interpreters;

import com.rocketchat.websocket.models.Connection;

public interface JSONInterpreter {
    void process(byte[] data, Connection connection);
}
