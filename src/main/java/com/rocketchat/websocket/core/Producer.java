package com.rocketchat.websocket.core;

import com.rocketchat.models.message.Message;

public interface Producer {
    Message sendMessage();
    void updateList(Message message);
}
