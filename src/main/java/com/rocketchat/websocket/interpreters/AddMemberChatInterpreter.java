package com.rocketchat.websocket.interpreters;

import com.rocketchat.core.JsonDecoder;
import com.rocketchat.core.services.AddMemberChatService;
import com.rocketchat.dtos.AddMemberDto;
import com.rocketchat.websocket.core.JSONInterpreter;
import com.rocketchat.websocket.models.Connection;

// TODO
public class AddMemberChatInterpreter implements JSONInterpreter {

    private JsonDecoder jsonDecoder;
    private AddMemberChatService addMemberChatService;

    public AddMemberChatInterpreter(JsonDecoder jsonDecoder) {
        this.jsonDecoder = jsonDecoder;
        this.addMemberChatService = new AddMemberChatService();
    }

    @Override
    public void process(String type, byte[] data, Connection connection) {
        jsonDecoder.fromJson(new String(data), AddMemberDto.class).ifPresent(addMemberDto -> {
            addMemberChatService.execute();
        });
    }

    @Override
    public boolean isSupported(String type) {
        return type.equals("add_member");
    }
}
