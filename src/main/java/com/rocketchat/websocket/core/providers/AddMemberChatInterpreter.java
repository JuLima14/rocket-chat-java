package com.rocketchat.websocket.core.providers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.AddMemberDto;
import com.rocketchat.models.message.Message;


public class AddMemberChatInterpreter implements JSONInterpreter {

    Gson gson;

    public AddMemberChatInterpreter(Gson gson) {
        this.gson = gson;
    }

    public void getMessage(byte[] data) {
        try{
            AddMemberDto message = gson.fromJson(new String(data), AddMemberDto.class);
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
