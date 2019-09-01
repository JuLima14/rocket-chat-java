package com.rocketchat.websocket.interpreters;

import com.rocketchat.websocket.models.Connection;

import java.util.List;

public class JSONInterpreterProcessor implements JSONInterpreter {

    private List<JSONInterpreter> interpreters;

    public JSONInterpreterProcessor(List<JSONInterpreter> interpreters) {
        this.interpreters = interpreters;
    }

    @Override
    public void process(byte[] data, Connection connection) {
        interpreters.stream().forEach(jsonInterpreter -> jsonInterpreter.process(data, connection));
    }
}
