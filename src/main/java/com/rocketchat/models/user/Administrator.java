package com.rocketchat.models.user;

import java.util.Date;

public class Administrator extends User {
    public Administrator(String name, String urlProfileImage, String phoneNumber, String stateProfile, Date lastConnectionDate) {
        super(name, urlProfileImage, phoneNumber, stateProfile, lastConnectionDate);
    }
}
