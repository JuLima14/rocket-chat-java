package com.rocketchat.websocket.interpreters;

import com.rocketchat.websocket.core.JSONInterpreter;
import com.rocketchat.websocket.models.Connection;

import java.util.List;

public class JSONInterpreterProcessor implements JSONInterpreter {

    private List<JSONInterpreter> interpreters;

    public JSONInterpreterProcessor(List<JSONInterpreter> interpreters) {
        this.interpreters = interpreters;
    }

    @Override
    public void process(String type, byte[] data, Connection connection) {
        interpreters.stream()
                .filter(interpreter -> interpreter.isSupported(type))
                .forEach(jsonInterpreter -> jsonInterpreter.process(type, data, connection));
    }

    @Override
    public boolean isSupported(String type) {
        return interpreters.stream()
                .anyMatch(interpreter -> interpreter.isSupported(type));
    }
}
