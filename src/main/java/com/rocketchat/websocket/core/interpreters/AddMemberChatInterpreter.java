package com.rocketchat.websocket.core.interpreters;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.AddMemberDto;
import com.rocketchat.websocket.models.Connection;


public class AddMemberChatInterpreter implements JSONInterpreter {

    private Gson gson;

    public AddMemberChatInterpreter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void process(byte[] data, Connection connection) {
        try {
            AddMemberDto message = gson.fromJson(new String(data), AddMemberDto.class).validate();

        } catch (JsonSyntaxException e) {
            System.out.println("The message is not a AddMemberDto");
        }
    }
}
