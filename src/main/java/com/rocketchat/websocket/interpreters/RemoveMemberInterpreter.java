package com.rocketchat.websocket.interpreters;

import com.rocketchat.core.JsonDecoder;
import com.rocketchat.core.services.RemoveMemberService;
import com.rocketchat.dtos.RemoveMemberDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.storage.Storage;
import com.rocketchat.core.bigqueue.BigQueue;
import com.rocketchat.websocket.core.JSONInterpreter;
import com.rocketchat.websocket.models.Connection;

public class RemoveMemberInterpreter implements JSONInterpreter {

    private final JsonDecoder jsonDecoder;
    private final RemoveMemberService removeMemberService;

    public RemoveMemberInterpreter(JsonDecoder jsonDecoder, Storage<Chat> storageChat, BigQueue bigQueue) {
        this.jsonDecoder = jsonDecoder;
        this.removeMemberService = new RemoveMemberService(storageChat,bigQueue);
    }

    @Override
    public void process(String type, byte[] data, Connection connection) {
            jsonDecoder.fromJson(new String(data), RemoveMemberDto.class).ifPresent( removeMemberDto -> {
                removeMemberService.execute(removeMemberDto);
            });
    }

    @Override
    public boolean isSupported(String type) {
        return type.equals("remove_member");
    }
}
