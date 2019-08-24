package com.rocketchat.models.message;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;

import java.util.Date;

public class TextMessage extends Message {
    private String text;

    public TextMessage(User userSender, Chat chat, Date sendDate, String text) {
        super(userSender, chat, sendDate);
        this.text = text;
    }

    @Override
    public byte[] getMessage() {
        return text.getBytes();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
