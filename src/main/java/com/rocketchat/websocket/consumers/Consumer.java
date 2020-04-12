package com.rocketchat.websocket.consumers;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.message.Message;
import com.rocketchat.models.user.User;

import java.io.IOException;

public interface Consumer {
    Chat getChat();

    User getUser();
<<<<<<< HEAD:src/main/java/com/rocketchat/websocket/consumers/Consumer.java
=======

>>>>>>> master:src/main/java/com/rocketchat/websocket/core/consumers/Consumer.java
    Boolean receiveMessage(Message message) throws IOException;
}
