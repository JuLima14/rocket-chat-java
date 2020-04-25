package com.rocketchat.websocket.interpreters;

import com.rocketchat.core.JsonDecoder;
import com.rocketchat.core.services.SendMessageService;
import com.rocketchat.dtos.SendMessageDto;
import com.rocketchat.websocket.consumers.MessageConsumer;
import com.rocketchat.websocket.core.BigQueue;
import com.rocketchat.websocket.models.Connection;
import com.rocketchat.websocket.producers.Producer;

public class SendMessageInterpreter implements JSONInterpreter {

    private JsonDecoder jsonDecoder;
    private SendMessageService sendMessageService;

    public SendMessageInterpreter(JsonDecoder jsonDecoder, Producer producer, BigQueue bigQueue) {
        this.jsonDecoder = jsonDecoder;
        this.sendMessageService = new SendMessageService(producer, bigQueue);
    }

    @Override
    public void process(String type, byte[] data, Connection connection) {
        jsonDecoder.fromJson(new String(data), SendMessageDto.class)
                .ifPresent(sendMessageDto -> {
                    sendMessageService.execute(sendMessageDto, connection);
                });
    }

    @Override
    public boolean isSupported(String type) {
        return type.equals("send_message");
    }
}
