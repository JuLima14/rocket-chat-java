package com.rocketchat.websocket.core;

import com.rocketchat.websocket.core.consumers.Consumer;
import com.rocketchat.websocket.core.producers.Producer;
import com.rocketchat.models.message.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueueExecutor implements Runnable {

    private Producer producer;
    private static List<Consumer> consumers = new ArrayList<>();

    public QueueExecutor(Producer producer) {
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

    public static void addConsumer(Consumer consumer) {
        if(!consumers.contains(consumer)) {
            consumers.add(consumer);
        }
    }

    public static void removeConsumer(Consumer consumer) {
        consumers.remove(consumer);
    }
}
