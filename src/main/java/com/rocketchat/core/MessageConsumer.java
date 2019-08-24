package com.rocketchat.core;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.connection.Connection;
import com.rocketchat.models.message.Message;

import java.io.IOException;

public class MessageConsumer implements Consumer {

    private Connection connection;
    private Chat chat;

    public MessageConsumer(Chat chat, Connection connection){
        this.chat = chat;
        this.connection = connection;
    }

    @Override
    public Boolean receiveMessage(Message message) throws IOException {
        if(message.getChat().equals(chat)) {
            connection.sendMessage(message);
            return true;
        }
        return false;
    }
}
