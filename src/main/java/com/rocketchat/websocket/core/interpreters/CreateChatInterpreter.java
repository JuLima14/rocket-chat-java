package com.rocketchat.websocket.core.providers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.CreateChatDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.storage.Storage;

public class CreateChatInterpreter implements JSONInterpreter{

    Gson gson;
    Storage<Chat> storageChat;

    public CreateChatInterpreter(Gson gson, Storage<Chat> storageChat) {
        this.gson = gson;
        this.storageChat = storageChat;
    }

    public void getMessage(byte[] data) {

        try{
            CreateChatDto message = gson.fromJson(new String(data), CreateChatDto.class);
            message.validate();

            if(!storageChat.get().contains(message.getChat())) {
                storageChat.set(message.getChat());
            }
            // do the stuff necessary to remove a chat
        }catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(byte[] data) {

    }
}
