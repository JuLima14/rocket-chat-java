package com.rocketchat.websocket.core.providers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.SendMessageDto;
import com.rocketchat.websocket.core.producers.Producer;

public class SendMessageInterpreter implements JSONInterpreter {

    Gson gson;
    Producer producer;

    public SendMessageInterpreter(Gson gson, Producer producer) {
        this.gson = gson;
        this.producer = producer;
    }

    @Override
    public void process(byte[] data) {
        try{
            SendMessageDto message = gson.fromJson(new String(data), SendMessageDto.class);
            message.validate();
            producer.updateList(message.getMessage());
            // do the stuff necessary to remove a chat
        }catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
