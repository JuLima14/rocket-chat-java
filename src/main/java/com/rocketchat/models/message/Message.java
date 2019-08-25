package com.rocketchat.models.message;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import java.util.Date;

public class Message {

    private User userSender;
    private Date sendDate;
    private Chat chat;
    private byte[] data;

    public Message(User userSender, Chat chat, Date sendDate, String data) {
        this.userSender = userSender;
        this.sendDate = sendDate;
        this.chat = chat;
        this.data = data.getBytes();
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

    public byte[] getMessage() {
        return data;
    }
}
