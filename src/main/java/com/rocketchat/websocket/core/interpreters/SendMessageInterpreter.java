package com.rocketchat.websocket.core.interpreters;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.SendMessageDto;
import com.rocketchat.websocket.core.QueueExecutor;
import com.rocketchat.websocket.core.consumers.MessageConsumer;
import com.rocketchat.websocket.core.producers.Producer;
import com.rocketchat.websocket.models.Connection;

public class SendMessageInterpreter implements JSONInterpreter {

    private Gson gson;
    private Producer producer;

    public SendMessageInterpreter(Gson gson, Producer producer) {
        this.gson = gson;
        this.producer = producer;
    }

    @Override
    public void process(byte[] data, Connection connection) {
        try{
            SendMessageDto message = gson.fromJson(new String(data), SendMessageDto.class).validate();

            QueueExecutor.addConsumer(new MessageConsumer(message.getChat(),
                    message.getMessage().getUserSender(),
                    connection));

            producer.updateList(message.getMessage());

        }catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
