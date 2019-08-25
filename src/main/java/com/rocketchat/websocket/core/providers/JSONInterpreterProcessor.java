package com.rocketchat.websocket.core.providers;

import com.google.gson.Gson;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JSONInterpreterProcessor implements JSONInterpreter {

    private List<JSONInterpreter> interpreters;

    // TODO: Inject the list of providers
    public JSONInterpreterProcessor(List<JSONInterpreter> interpreters) {
        this.interpreters = interpreters;
    }

    @Override
    public void process(byte[] data) {
        interpreters.stream().forEach(jsonInterpreter -> jsonInterpreter.process(data));
    }
}
