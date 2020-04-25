package com.rocketchat.core.services;

import com.rocketchat.dtos.DeleteChatDto;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.storage.Storage;

public class DeleteChatService {

    private final Storage<Chat> storageChat;

    public DeleteChatService(Storage<Chat> storageChat) {
        this.storageChat = storageChat;
    }

    public void execute(DeleteChatDto deleteChatDto) {
        storageChat.get()
                .stream()
                .filter(chat -> chat.getId().equals(deleteChatDto.getChat().getId()))
                .findFirst()
                .ifPresent(chatFound -> {
                    if (chatFound.getUsers().size() == 1) {
                        storageChat.remove(chatFound);
                    } else {
                        // TODO: si hay mas de uno, remover el usuario
                    }
                });
    }
}
