package com.rocketchat.websocket.core.interpreters;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.DeleteChatDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.models.Connection;

import java.util.Optional;

public class DeleteChatInterpreter implements JSONInterpreter {

    private Gson gson;
    private Storage<Chat> storageChat;

    public DeleteChatInterpreter(Gson gson, Storage<Chat> storageChat) {
        this.gson = gson;
        this.storageChat = storageChat;
    }

    @Override
    public void process(byte[] data, Connection connection) {
        try {
            DeleteChatDto message = gson.fromJson(new String(data), DeleteChatDto.class).validate();

            Optional<Chat> chatFound = storageChat.get()
                    .stream()
                    .filter(chat -> chat.getId().equals(message.getChat().getId()))
                    .findFirst();

            if(chatFound.isPresent()) {
                if(chatFound.get().getUsers().size() < 2) {
                    storageChat.remove(chatFound.get());
                }
            }
        } catch (JsonSyntaxException e) {
            System.out.println("The message is not a DeleteChatDto");
        }
    }
}
