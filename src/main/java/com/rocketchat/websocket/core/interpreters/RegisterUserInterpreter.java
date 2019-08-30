package com.rocketchat.websocket.core.interpreters;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.RegisterUserDto;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.models.Connection;

public class RegisterUserInterpreter implements JSONInterpreter {

    private Gson gson;
    private Storage<User> userStorage;

    public RegisterUserInterpreter(Gson gson, Storage<User> userStorage) {
        this.gson = gson;
        this.userStorage = userStorage;
    }

    @Override
    public void process(byte[] data, Connection connection) {
        try {
            RegisterUserDto message = gson.fromJson(new String(data), RegisterUserDto.class).validate();

        } catch (JsonSyntaxException e) {
            System.out.println("The message is not a RegisterUserDto");
        }
    }
}
