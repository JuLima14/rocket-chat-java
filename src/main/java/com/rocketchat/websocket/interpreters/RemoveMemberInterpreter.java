package com.rocketchat.websocket.interpreters;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.RemoveMemberDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.consumers.MessageConsumer;
import com.rocketchat.websocket.core.BigQueue;
import com.rocketchat.websocket.models.Connection;

import java.util.Optional;

public class RemoveMemberInterpreter implements JSONInterpreter {

    private Gson gson;
    private Storage<Chat> storageChat;
    private final BigQueue bigQueue;

    public RemoveMemberInterpreter(Gson gson, Storage<Chat> storageChat, BigQueue bigQueue) {
        this.gson = gson;
        this.storageChat = storageChat;
        this.bigQueue = bigQueue;
    }

    @Override
    public void process(byte[] data, Connection connection) {
        try {
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
                    bigQueue.removeConsumer(userFound.get(), chatFound.get());
                    bigQueue.removeConsumer(userFound.get());
                }
            }

        } catch (JsonSyntaxException e) {
            System.out.println("The message is not a RemoveMemberDto");
        }
    }
}
