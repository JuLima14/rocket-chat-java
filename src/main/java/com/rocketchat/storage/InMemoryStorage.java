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
        System.out.println("Value set:" + value);
        storage.add(value);
    }

    @Override
    public Boolean remove(P value) {
        return storage.remove(value);
    }
}
