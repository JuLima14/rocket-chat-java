package com.rocketchat.core.services;

import com.rocketchat.dtos.CreateChatDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.storage.Storage;

public class CreateChatService {

    private final Storage<Chat> storageChat;

    public CreateChatService(Storage<Chat> storageChat) {
        this.storageChat = storageChat;
    }

    public void execute(CreateChatDto createChatDto) {
        if(!storageChat.get().contains(createChatDto.getChat())) {
            storageChat.set(createChatDto.getChat());
        }
    }
}