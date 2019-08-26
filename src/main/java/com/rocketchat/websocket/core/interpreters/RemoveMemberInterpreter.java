package com.rocketchat.websocket.core.providers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.rocketchat.dtos.RemoveMemberDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.storage.Storage;

public class RemoveMemberInterpreter implements JSONInterpreter {

    Gson gson;
    Storage<Chat> storageChat;

    public RemoveMemberInterpreter(Gson gson, Storage<Chat> storageChat) {
        this.gson = gson;
        this.storageChat = storageChat;
    }

    @Override
    public void process(byte[] data) {
        try{
            RemoveMemberDto message = gson.fromJson(new String(data), RemoveMemberDto.class);
            message.validate();
            // do the stuff necessary to remove a chat
        }catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
