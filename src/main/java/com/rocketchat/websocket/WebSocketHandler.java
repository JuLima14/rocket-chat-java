package com.rocketchat.websocket;

import com.google.gson.Gson;

import com.rocketchat.core.Producer;
import com.rocketchat.core.QueueExecutor;
import com.rocketchat.models.connection.Connection;
import com.rocketchat.models.message.DataMessage;
import com.rocketchat.models.message.TextMessage;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

import java.net.InetSocketAddress;

import java.util.concurrent.ConcurrentHashMap;

@WebSocket
public class WebSocketHandler {

    private final ConcurrentHashMap<InetSocketAddress, Connection> sessions = new ConcurrentHashMap<>();
    private final Producer producer;
    private final Gson gson;
    private final QueueExecutor executor;

    public WebSocketHandler(QueueExecutor executor, Producer producer) {
        this.producer = producer;
        this.executor = executor;
        this.gson = new Gson();

        executor.start();
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        sessions.put(session.getRemoteAddress(), new Connection(session));
        System.out.println("Connect: " + session.getRemoteAddress().getAddress());

        try {
            session.getRemote().sendString("Hello Webbrowser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        sessions.remove(session.getRemoteAddress());
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @OnWebSocketMessage
    public void onMessage(Session session, byte[] data, int offset, int length) {
        System.out.println("New Binary Message Received");

       DataMessage dataMessage = gson.fromJson(new String(data), DataMessage.class);

        producer.updateList(dataMessage);
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        System.out.println("New Text Message Received");

        TextMessage textMessage = gson.fromJson(message, TextMessage.class);

        producer.updateList(textMessage);
    }
}