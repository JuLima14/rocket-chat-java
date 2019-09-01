package com.rocketchat.websocket;

import com.google.gson.Gson;
import com.rocketchat.dtos.*;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.message.Message;
import com.rocketchat.models.user.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MockGenerator {

    public static MockGenerator instance = new MockGenerator();

    public User getUser(String name, String urlProfileImage, String phoneNumber, String stateProfile, Date date) {
        return new User(name, urlProfileImage, phoneNumber, stateProfile, date);
    }

    public Chat getChat() {
        User user1 = getUser("Julián",
                "http://urlToImage.com",
                "123456789",
                "Hey im using rocket chat",
                Calendar.getInstance().getTime());

        User user2 = getUser("Pedro",
                "http://urlToImage.com",
                "987654321",
                "(U)",
                Calendar.getInstance().getTime());

        List<User> users = new ArrayList<User>();

        users.add(user1);
        users.add(user2);

        Chat chat = new Chat(users);
        return chat;
    }

    public AddMemberDto getAddMemberDto() {
        Chat chat = getChat();
        User user = getUser("Julián",
                "http://urlToImage.com",
                "123456789",
                "Hey im using rocket chat",
                Calendar.getInstance().getTime());

        AddMemberDto addMemberDto = new AddMemberDto(chat, user, "add_member");

        System.out.println(new Gson().toJson(addMemberDto));

        return addMemberDto;
    }

    public CreateChatDto getCreateChatDto() {
        CreateChatDto createChatDto = new CreateChatDto("create_chat", getChat());
        System.out.println(new Gson().toJson(createChatDto));
        return createChatDto;
    }

    public DeleteChatDto getDeleteChatDto() {
        DeleteChatDto deleteChatDto = new DeleteChatDto("delete_chat", getChat());
        System.out.println(new Gson().toJson(deleteChatDto));
        return deleteChatDto;
    }

    public RegisterUserDto getRegisterUserDto() {
        User user = getUser("Julián",
                "http://urlToImage.com",
                "123456789",
                "Hey im using rocket chat",
                Calendar.getInstance().getTime());
        RegisterUserDto registerUserDto = new RegisterUserDto("register_user", user);
        System.out.println(new Gson().toJson(registerUserDto));
        return registerUserDto;
    }

    public RemoveMemberDto getRemoveMemberDto() {
        User user = getUser("Julián",
                "http://urlToImage.com",
                "123456789",
                "Hey im using rocket chat",
                Calendar.getInstance().getTime());
        RemoveMemberDto removeMemberDto = new RemoveMemberDto("remove_member", getChat(), user);
        System.out.println(new Gson().toJson(removeMemberDto));
        return removeMemberDto;
    }

    public SendMessageDto getSendMessageDto() {
        User user = getUser("Julián",
                "http://urlToImage.com",
                "123456789",
                "Hey im using rocket chat",
                Calendar.getInstance().getTime());

        Message message = new Message(user, getChat(),
                Calendar.getInstance().getTime(),
                "Hola, todo bien ?");

        SendMessageDto sendMessageDto = new SendMessageDto("send_message", getChat(), message);
        System.out.println(new Gson().toJson(sendMessageDto));
        return sendMessageDto;
    }
}
