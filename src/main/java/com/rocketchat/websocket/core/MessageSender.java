package com.rocketchat.websocket.core;

import com.rocketchat.models.message.Message;

import java.io.IOException;

public interface MessageSender {
    void sendMessage(Message message) throws IOException;
}
