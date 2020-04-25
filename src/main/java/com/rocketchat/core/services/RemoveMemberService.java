package com.rocketchat.core.services;

import com.rocketchat.dtos.RemoveMemberDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.core.BigQueue;

public class RemoveMemberService {

    private final Storage<Chat> storageChat;
    private final BigQueue bigQueue;

    public RemoveMemberService(Storage<Chat> storageChat, BigQueue bigQueue) {
        this.storageChat = storageChat;
        this.bigQueue = bigQueue;
    }

    public void execute(RemoveMemberDto removeMemberDto) {
        storageChat.get()
                .stream()
                .filter(chat -> chat.getId().equals(removeMemberDto.getChat().getId()))
                .findFirst().ifPresent( chatFound -> {
            chatFound.getUsers()
                    .stream()
                    .filter(user -> user.getPhoneNumber().equals(removeMemberDto.getUser().getPhoneNumber()))
                    .findFirst().ifPresent(userFound -> {
                chatFound.getUsers().remove(removeMemberDto.getUser());
                storageChat.set(chatFound);
                bigQueue.removeConsumer(userFound, chatFound);
            });
        });
    }
}
