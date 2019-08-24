package com.rocketchat.models.message;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;

import java.util.Date;

public class DataMessage extends Message {

    private byte[] data;

    public DataMessage(User userSender, Chat chat, Date sendDate, byte[] data) {
        super(userSender, chat, sendDate);

        this.data = data;
    }

    @Override
    public byte[] getMessage() {
        return data;
    }
}
