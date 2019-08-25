package com.rocketchat.websocket.core.providers;

public interface Provider {
    void process(byte[] data);
}
