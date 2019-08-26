package com.rocketchat.websocket.core.providers;

import java.util.List;

public class JSONInterpreterProcessor implements JSONInterpreter {

    private List<JSONInterpreter> interpreters;

    public JSONInterpreterProcessor(List<JSONInterpreter> interpreters) {
        this.interpreters = interpreters;
    }

    @Override
    public void process(byte[] data) {
        interpreters.stream().forEach(jsonInterpreter -> jsonInterpreter.process(data));
    }
}
