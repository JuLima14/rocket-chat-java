package com.rocketchat.websocket.core.providers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.DeleteChatDto;
import com.rocketchat.models.message.Message;

public class DeleteChatInterpreter implements JSONInterpreter {

    Gson gson;

    public DeleteChatInterpreter(Gson gson) {
        this.gson = gson;
    }

    public void getMessage(byte[] data) {
        try{
            DeleteChatDto message = gson.fromJson(new String(data), DeleteChatDto.class);
            message.validate();
            // do the stuff necessary to remove a chat
        }catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(byte[] data) {

    }
}
