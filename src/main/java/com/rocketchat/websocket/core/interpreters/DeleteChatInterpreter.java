package com.rocketchat.websocket.core.providers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.DeleteChatDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.storage.Storage;

import java.util.Optional;

public class DeleteChatInterpreter implements JSONInterpreter {

    Gson gson;
    Storage<Chat> storageChat;

    public DeleteChatInterpreter(Gson gson, Storage<Chat> storageChat) {
        this.gson = gson;
        this.storageChat = storageChat;
    }

    @Override
    public void process(byte[] data) {
        try{
            DeleteChatDto message = gson.fromJson(new String(data), DeleteChatDto.class);
            message.validate();
            Optional<Chat> chatFound = storageChat.get()
                    .stream()
                    .filter(chat -> chat.getId() == message.getChat().getId())
                    .findFirst();

            if(chatFound.isPresent()) {
                if(chatFound.get().getUsers().size() < 2) {
                    storageChat.remove(chatFound.get());
                }
            }
            // do the stuff necessary to remove a chat
        }catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
