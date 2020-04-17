package com.rocketchat.core;

import static spark.Spark.staticFileLocation;
import static spark.Spark.port;
import static spark.Spark.init;

final public class ServerImpl {
    public ServerImpl(ServerBuilder server) {
        staticFileLocation("static");
        port(8080);

        server.build();
        init();
    }
}
