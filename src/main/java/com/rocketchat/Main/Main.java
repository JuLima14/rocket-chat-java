
package com.rocketchat.Main;

import com.rocketchat.core.ServerImpl;
import com.rocketchat.core.WebSocketServer;

public class Main {
    public static void main(String[] args) {
        final WebSocketServer wss = new WebSocketServer();
        new ServerImpl(wss);
    }
}
