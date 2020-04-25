package com.rocketchat.websocket.interpreters;

import com.rocketchat.core.JsonDecoder;
import com.rocketchat.core.services.DeleteChatService;
import com.rocketchat.dtos.DeleteChatDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.models.Connection;

public class DeleteChatInterpreter implements JSONInterpreter {

    private JsonDecoder jsonDecoder;
    private DeleteChatService deleteChatService;

    public DeleteChatInterpreter(JsonDecoder jsonDecoder, Storage<Chat> storageChat) {
        this.jsonDecoder = jsonDecoder;
        this.deleteChatService = new DeleteChatService(storageChat);
    }

    @Override
    public void process(String type, byte[] data, Connection connection) {
        jsonDecoder.fromJson(new String(data), DeleteChatDto.class).ifPresent(deleteChatDto -> {
            deleteChatService.execute(deleteChatDto);
        });
    }

    @Override
    public boolean isSupported(String type) {
        return type.equals("delete_chat");
    }
}
