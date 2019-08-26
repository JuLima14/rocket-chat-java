package com.rocketchat.websocket.core.consumers;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.websocket.models.Connection;
import com.rocketchat.models.message.Message;

import java.io.IOException;

public class MessageConsumer implements Consumer {

    private Connection connection;
    private Chat chat;
    private User user;

    public MessageConsumer(Chat chat, User user, Connection connection){
        this.chat = chat;
        this.connection = connection;
        this.user = user;
    }

    @Override
    public Boolean receiveMessage(Message message) throws IOException {
        if(message.getChat().equals(chat) && !user.equals(message.getUserSender())) {
            connection.sendMessage(message);
            return true;
        }
        return false;
    }
}
