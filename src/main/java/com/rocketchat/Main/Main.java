
package com.rocketchat.Main;

import com.rocketchat.core.ServerBuilder;
import com.rocketchat.core.TypeServer;

public class Main {
    public static void main(String[] args) {
        new ServerBuilder(TypeServer.WEB_SOCKET);
    }
}
