
package com.rocketchat.Main;

import com.rocketchat.WebSocket.WebSocketHandler;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        webSocket("/socket", WebSocketHandler.class);
        staticFileLocation("static");
        port(8080);
        init();
    }
}
