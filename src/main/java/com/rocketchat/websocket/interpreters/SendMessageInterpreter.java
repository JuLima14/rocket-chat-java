package com.rocketchat.websocket.interpreters;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.SendMessageDto;
import com.rocketchat.websocket.consumers.MessageConsumer;
import com.rocketchat.websocket.core.BigQueue;
import com.rocketchat.websocket.models.Connection;
import com.rocketchat.websocket.producers.Producer;

public class SendMessageInterpreter implements JSONInterpreter {

    private Gson gson;
    private Producer producer;
    private BigQueue bigQueue;

    public SendMessageInterpreter(Gson gson, Producer producer, BigQueue bigQueue) {
        this.gson = gson;
        this.producer = producer;
        this.bigQueue = bigQueue;
    }

    @Override
    public void process(byte[] data, Connection connection) {
        try {
            SendMessageDto message = gson.fromJson(new String(data), SendMessageDto.class).validate();

            bigQueue.addConsumer(new MessageConsumer(message.getChat(),
                    message.getMessage().getUserSender(),
                    connection));

            producer.updateList(message.getMessage());

        } catch (JsonSyntaxException e) {
            System.out.println("The message is not a SendMessageDto");
        }
    }
}
