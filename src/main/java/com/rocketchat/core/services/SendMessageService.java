package com.rocketchat.core.services;

import com.rocketchat.dtos.SendMessageDto;
import com.rocketchat.websocket.consumers.MessageConsumer;
import com.rocketchat.websocket.core.BigQueue;
import com.rocketchat.websocket.models.Connection;
import com.rocketchat.websocket.producers.Producer;

public class SendMessageService {

    private final Producer producer;
    private final BigQueue bigQueue;

    public SendMessageService(Producer producer, BigQueue bigQueue) {
        this.producer = producer;
        this.bigQueue = bigQueue;
    }

    public void execute(SendMessageDto sendMessageDto, Connection connection) {
        bigQueue.addConsumer(new MessageConsumer(sendMessageDto.getChat(),
                sendMessageDto.getMessage().getUserSender(),
                connection));

        producer.updateList(sendMessageDto.getMessage());
    }
}
