package com.rocketchat.websocket.core.consumers;

import com.rocketchat.models.message.Message;

import java.io.IOException;

public interface Consumer {
    Boolean receiveMessage(Message message) throws IOException;
}
