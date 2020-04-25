package com.rocketchat.core;

import java.util.Optional;

public interface JsonDecoder {
    public <T> Optional<T> fromJson(String value, Class<T> type);
}
