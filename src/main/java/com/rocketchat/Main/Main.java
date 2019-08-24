
package com.rocketchat.Main;

import com.rocketchat.core.MessageProducer;
import com.rocketchat.core.Producer;
import com.rocketchat.core.QueueExecutor;
import com.rocketchat.websocket.WebSocketHandler;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Producer producer = new MessageProducer();
        QueueExecutor executor = new QueueExecutor(producer);
        WebSocketHandler socket = new WebSocketHandler(executor, producer);

        webSocket("/socket", socket);

        staticFileLocation("static");

        port(8080);
        init();
    }
}
