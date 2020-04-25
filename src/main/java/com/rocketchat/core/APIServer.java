package com.rocketchat.core;

import com.rocketchat.core.bigqueue.BigQueue;
import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.user.User;
import com.rocketchat.storage.InMemoryStorage;
import com.rocketchat.storage.Storage;
import com.rocketchat.websocket.producers.MessageProducer;
import com.rocketchat.websocket.core.Producer;

final public class APIServer implements ServerBuilder {

    @Override
    public final void build() {
        // TODO
        Storage<User> userStorage = new InMemoryStorage<>();
        Storage<Chat> chatStorage = new InMemoryStorage<>();

        Producer producer = new MessageProducer();
        BigQueue bigQueue = new BigQueue(producer);
    }
}
