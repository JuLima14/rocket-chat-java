package com.rocketchat.storage;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStorage<P> implements Storage<P> {

    private List<P> storage;

    public InMemoryStorage() {
        storage = new ArrayList<P>();
    }

    @Override
    public List<P> get() {
        return storage;
    }

    @Override
    public void set(P value) {
        storage.add(value);
    }
}
