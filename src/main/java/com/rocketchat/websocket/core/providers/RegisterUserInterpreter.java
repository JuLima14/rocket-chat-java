package com.rocketchat.websocket.core.providers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.RegisterUserDto;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;

public class RegisterUserInterpreter implements JSONInterpreter {

    private Gson gson;
    private Storage<User> userStorage;

    public RegisterUserInterpreter(Gson gson, Storage<User> userStorage) {
        this.gson = gson;
        this.userStorage = userStorage;
    }

    @Override
    public void process(byte[] data) {
        try{
            RegisterUserDto message = gson.fromJson(new String(data), RegisterUserDto.class);
            message.validate();
            // do the stuff necessary to remove a chat
        }catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
