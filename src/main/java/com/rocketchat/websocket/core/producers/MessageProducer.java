package com.rocketchat.websocket.core.producers;

import com.rocketchat.models.message.Message;

import java.util.*;

public class MessageProducer implements Producer {

    private final Queue<Message> messages;

    public MessageProducer() {
        this.messages = new LinkedList();
    }

    public void updateList(Message message) {
        messages.add(message);
    }

    @Override
    public Message sendMessage() {
        return messages.peek();
    }
}
