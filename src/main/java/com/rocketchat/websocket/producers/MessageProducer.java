package com.rocketchat.websocket.producers;

import com.rocketchat.models.message.Message;

import java.util.LinkedList;
import java.util.Queue;

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
