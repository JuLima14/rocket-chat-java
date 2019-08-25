package com.rocketchat.models.chat;

import com.rocketchat.models.message.Message;

import com.rocketchat.models.user.User;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private List<User> admins;
    private List<User> users;

    public Chat(List<User> users) {
        this.admins = new ArrayList<>();
        this.users = users;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
