package com.rocketchat.websocket.consumers;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.message.Message;
import com.rocketchat.models.user.User;

import java.io.IOException;

public interface Consumer {
    Chat getChat();
    User getUser();
    Boolean receiveMessage(Message message) throws IOException;
}
