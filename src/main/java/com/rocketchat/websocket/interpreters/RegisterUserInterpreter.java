package com.rocketchat.websocket.interpreters;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rocketchat.dtos.RegisterUserDto;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.models.Connection;

import java.util.Optional;

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

            Optional<User> userFound = userStorage.get().stream()
                    .filter(user -> user.getPhoneNumber().equals(message.getUser().getPhoneNumber()))
                    .findFirst();

            if(!userFound.isPresent()) {
                userStorage.set(message.getUser());
            }
        }
        catch (NullPointerException e) {
            System.out.println("The User is Null");
        }
        catch (JsonSyntaxException e) {
            System.out.println("The message is not a RegisterUserDto");
        }
    }
}
