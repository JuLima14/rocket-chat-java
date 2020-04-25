package com.rocketchat.websocket.interpreters;

import com.rocketchat.core.JsonDecoder;
import com.rocketchat.core.services.CreateChatService;
import com.rocketchat.dtos.CreateChatDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.models.Connection;

public class CreateChatInterpreter implements JSONInterpreter {

    private JsonDecoder jsonDecoder;
    private CreateChatService createChatService;

    public CreateChatInterpreter(JsonDecoder jsonDecoder, Storage<Chat> storageChat) {
        this.jsonDecoder = jsonDecoder;
        this.createChatService = new CreateChatService(storageChat);
    }

    @Override
    public void process(String type, byte[] data, Connection connection) {
        jsonDecoder.fromJson(new String(data), CreateChatDto.class).ifPresent(createChatDto -> {
            createChatService.execute(createChatDto);
        });
    }

    @Override
    public boolean isSupported(String type) {
        return type.equals("create_chat");
    }
}
