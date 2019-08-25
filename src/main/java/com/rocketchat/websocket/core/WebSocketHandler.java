package com.rocketchat.websocket.core;

import com.rocketchat.websocket.core.providers.JSONInterpreter;
import com.rocketchat.websocket.models.Connection;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.net.InetSocketAddress;

import java.util.concurrent.ConcurrentHashMap;

@WebSocket
public class WebSocketHandler {

    private final ConcurrentHashMap<InetSocketAddress, Connection> sessions = new ConcurrentHashMap<>();
    private final JSONInterpreter interpreter;
    private final QueueExecutor executor;

    public WebSocketHandler(QueueExecutor executor, JSONInterpreter interpreter) {
        this.executor = executor;
        this.interpreter = interpreter;

        executor.start();
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connect: " + session.getRemoteAddress().getAddress());

        sessions.put(session.getRemoteAddress(), new Connection(session));
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);

        sessions.remove(session.getRemoteAddress());
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @OnWebSocketMessage
    public void onMessage(Session session, byte[] data, int offset, int length) {
        System.out.println("New Binary Message Received");

        interpreter.process(data);
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        System.out.println("New Text Message Received");

        interpreter.process(message.getBytes());
    }
}