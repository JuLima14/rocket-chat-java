package com.rocketchat.websocket.models;

import com.rocketchat.models.message.Message;
import com.rocketchat.models.user.User;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Connection implements MessageSender {

    private Session session;

    public Connection(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        session.getRemote().sendBytes(ByteBuffer.wrap(message.getMessage()));
    }
}
