package com.rocketchat.websocket.interpreters;

import com.rocketchat.core.JsonDecoder;
import com.rocketchat.core.services.RegisterUserService;
import com.rocketchat.dtos.RegisterUserDto;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.core.JSONInterpreter;
import com.rocketchat.websocket.models.Connection;

public class RegisterUserInterpreter implements JSONInterpreter {

    private JsonDecoder jsonDecoder;
    private RegisterUserService registerUserService;

    public RegisterUserInterpreter(JsonDecoder jsonDecoder, Storage<User> userStorage) {
        this.jsonDecoder = jsonDecoder;
        this.registerUserService = new RegisterUserService(userStorage);
    }

    @Override
    public void process(String type, byte[] data, Connection connection) {
        jsonDecoder.fromJson(new String(data), RegisterUserDto.class).ifPresent( registerUserDto -> {
            registerUserService.execute(registerUserDto);
        });
    }

    @Override
    public boolean isSupported(String type) {
        return type.equals("register_user");
    }
}
