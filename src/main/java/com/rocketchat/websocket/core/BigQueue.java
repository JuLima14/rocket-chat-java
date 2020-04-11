package com.rocketchat.websocket.core;

import com.rocketchat.models.chat.Chat;
import com.rocketchat.models.message.Message;
import com.rocketchat.models.user.User;
import com.rocketchat.websocket.consumers.Consumer;
import com.rocketchat.websocket.producers.Producer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BigQueue implements Runnable {

    private Producer producer;
    private static List<Consumer> consumers = new ArrayList<>();

    public BigQueue(Producer producer) {
        this.producer = producer;
        start();
    }

    public void start() {
        new Thread(this, "MessageQueueThread").start();
    }

    @Override
    public void run() {

        while(true) {
            if (consumers.size() > 0) {
                Message message = producer.sendMessage();
                List<Boolean> received = new ArrayList<>();

                for (Consumer consumer : consumers) {
                    try {
                        received.add(consumer.receiveMessage(message));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (received.size() == 0) {
                    producer.updateList(message);
                }
            }
            delayUntilNextRound();
        }
    }

    private void delayUntilNextRound() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public void addConsumer(Consumer consumer) {
        if(!consumers.contains(consumer)) {
            consumers.add(consumer);
        }
    }

    public void removeConsumer(User user, Chat chat) {
        Optional<Consumer> consumerFound = consumers.stream()
                .filter(consumer -> consumer.getUser()
                .getPhoneNumber().equals(user.getPhoneNumber()))
                .filter(consumer -> consumer.getChat().equals(chat)).findFirst();

        if (consumerFound.isPresent()) {
            consumers.remove(consumerFound.get());
        }
    }

    // ONLY USE IN CASE OF DISCONNECTION
    public void removeConsumer(User user) {
        Optional<Consumer> consumerFound = consumers.stream()
                .filter(consumer -> consumer
                        .getUser()
                        .getPhoneNumber().equals(user.getPhoneNumber()))
                .findFirst();

        if (consumerFound.isPresent()) {
            consumers.remove(consumerFound.get());
        }
    }
}
