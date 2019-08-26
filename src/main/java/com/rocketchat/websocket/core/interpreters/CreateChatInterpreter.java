package com.rocketchat.websocket.core.interpreters;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.CreateChatDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.models.Connection;

public class CreateChatInterpreter implements JSONInterpreter{

    private Gson gson;
    private Storage<Chat> storageChat;

    public CreateChatInterpreter(Gson gson, Storage<Chat> storageChat) {
        this.gson = gson;
        this.storageChat = storageChat;
    }

    @Override
    public void process(byte[] data, Connection connection) {
        try{
            CreateChatDto message = gson.fromJson(new String(data), CreateChatDto.class).validate();

            if(!storageChat.get().contains(message.getChat())) {
                storageChat.set(message.getChat());
            }
            // do the stuff necessary to remove a chat
        }catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
