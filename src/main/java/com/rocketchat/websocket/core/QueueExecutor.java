package com.rocketchat.websocket.core;

import com.rocketchat.websocket.core.consumers.Consumer;
import com.rocketchat.websocket.core.producers.Producer;
import com.rocketchat.models.message.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueueExecutor implements Runnable {

    Producer producer;
    List<Consumer> consumers;

    public QueueExecutor(Producer producer) {
        this.producer = producer;
        this.consumers = new ArrayList<>();
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
                        System.out.println("ejecuto");
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
        consumers.add(consumer);
    }

    public void removeConsumer(Consumer consumer) {
        consumers.remove(consumer);
    }
}
