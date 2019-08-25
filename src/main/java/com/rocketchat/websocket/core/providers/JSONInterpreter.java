package com.rocketchat.websocket.core.providers;

import com.rocketchat.models.message.Message;

import java.util.Optional;

public interface JSONInterpreter {
    void process(byte[] data);
}
