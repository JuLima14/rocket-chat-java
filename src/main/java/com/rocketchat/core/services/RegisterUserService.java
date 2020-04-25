package com.rocketchat.core.services;

import com.rocketchat.dtos.RegisterUserDto;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.Storage;

public class RegisterUserService {

    private final Storage<User> userStorage;

    public RegisterUserService(Storage<User> userStorage) {
        this.userStorage = userStorage;
    }

    public void execute(RegisterUserDto registerUserDto) {
        userStorage.get().stream()
                .filter(user -> user.getPhoneNumber().equals(registerUserDto.getUser().getPhoneNumber()))
                .findFirst()
                .ifPresent( userFound -> userStorage.set(registerUserDto.getUser()));
    }
}
