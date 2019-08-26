package com.rocketchat.websocket.core.interpreters;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.rocketchat.dtos.RemoveMemberDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.core.QueueExecutor;
import com.rocketchat.websocket.core.consumers.MessageConsumer;
import com.rocketchat.websocket.models.Connection;

import java.util.Optional;

public class RemoveMemberInterpreter implements JSONInterpreter {

    private Gson gson;
    private Storage<Chat> storageChat;

    public RemoveMemberInterpreter(Gson gson, Storage<Chat> storageChat) {
        this.gson = gson;
        this.storageChat = storageChat;
    }

    @Override
    public void process(byte[] data, Connection connection) {
        try{
            RemoveMemberDto message = gson.fromJson(new String(data), RemoveMemberDto.class).validate();

            Optional<Chat> chatFound = storageChat.get()
                    .stream()
                    .filter(chat -> chat.getId().equals(message.getChat().getId()))
                    .findFirst();

            if(chatFound.isPresent()) {
                Optional<User> userFound = chatFound.get()
                        .getUsers()
                        .stream()
                        .filter(user -> user.getPhoneNumber().equals(message.getUser().getPhoneNumber()))
                        .findFirst();

                if(userFound.isPresent()) {
                    chatFound.get().getUsers().remove(message.getUser());
                    storageChat.set(chatFound.get());
                    // TODO: remove consumers by chat and user
                    QueueExecutor.removeConsumer(new MessageConsumer(message.getChat(), userFound.get(), connection));
                }
            }

        }catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
