package com.rocketchat.websocket.interpreters;

import com.rocketchat.websocket.models.Connection;

import java.util.stream.Stream;

public interface JSONInterpreter {
    void process(String type, byte[] data, Connection connection);
    boolean isSupported(String type);
}
