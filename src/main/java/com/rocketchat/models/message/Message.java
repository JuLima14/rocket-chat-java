package com.rocketchat.models.message;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import java.util.Date;

public abstract class Message {

    protected User userSender;
    protected Date sendDate;
    protected Chat chat;

    public Message(User userSender, Chat chat, Date sendDate) {
        this.userSender = userSender;
        this.sendDate = sendDate;
        this.chat = chat;
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Chat getChat() { return chat; }

    public void setChat(Chat chat) { this.chat = chat; }

    public abstract byte[] getMessage();
}
