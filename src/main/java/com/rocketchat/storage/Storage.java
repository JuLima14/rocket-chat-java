package com.rocketchat.storage;

import java.util.List;

public interface Storage<T> {
    List<T> get();

    void set(T value);
}
