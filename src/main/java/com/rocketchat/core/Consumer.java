package com.rocketchat.core;

import com.rocketchat.models.message.Message;

import java.io.IOException;

public interface Consumer {
    Boolean receiveMessage(Message message) throws IOException;
}
