package com.rocketchat.dtos;

import com.google.gson.JsonSyntaxException;

public abstract class DataTransferObjectType {

    protected String type;

    public DataTransferObjectType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract void validate() throws JsonSyntaxException;
}
