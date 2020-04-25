package com.rocketchat.core;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Optional;

final public class GsonJsonDecoder implements JsonDecoder {

    private Gson gson;

    public GsonJsonDecoder(){
        this.gson = new Gson();
    }

    @Override
    public <T> Optional<T> fromJson(String value, Class<T> type) {
        Optional<T> object = Optional.empty();
        try {
            object = Optional.ofNullable(gson.fromJson(value, type));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return object;
    }
}
