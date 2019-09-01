package com.rocketchat.models.user;

import java.util.Date;

public class User {
    private String name;
    private String urlProfileImage;
    private String phoneNumber;
    private String stateProfile;
    private Date lastConnectionDate;

    public User(String name, String urlProfileImage, String phoneNumber, String stateProfile, Date lastConnectionDate) {
        this.name = name;
        this.urlProfileImage = urlProfileImage;
        this.phoneNumber = phoneNumber;
        this.stateProfile = stateProfile;
        this.lastConnectionDate = lastConnectionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlProfileImage() {
        return urlProfileImage;
    }

    public void setUrlProfileImage(String urlProfileImage) {
        this.urlProfileImage = urlProfileImage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStateProfile() {
        return stateProfile;
    }

    public void setStateProfile(String stateProfile) {
        this.stateProfile = stateProfile;
    }

    public Date getLastConnectionDate() {
        return lastConnectionDate;
    }

    public void setLastConnectionDate(Date lastConnectionDate) {
        this.lastConnectionDate = lastConnectionDate;
    }
}
