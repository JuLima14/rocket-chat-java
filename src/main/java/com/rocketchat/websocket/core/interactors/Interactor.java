package com.rocketchat.websocket.core.interactors;

public interface Interactor {

    void process(byte[] data);
}
