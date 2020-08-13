package io.github.boogiemonster1o1.usefulutilities.http;

import io.github.boogiemonster1o1.usefulutilities.UsefulUtilities;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class MonsterHttp {
    private static HttpServer server;
    public static void startServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(80), 0);
            server.createContext("/", new MonsterHttpHandler());
            server.setExecutor(null);
            server.start();
            UsefulUtilities.LOGGER.info("MonsterHTTP started on port 80");
        } catch (Exception e) {
            UsefulUtilities.LOGGER.error("MonsterHTTP failed to start", e);
        }
    }

    public static void stopServer(){
        try{
            server.stop(0);
            UsefulUtilities.LOGGER.info("MonsterHTTP stopped");
        } catch (Exception e) {
            e.printStackTrace();
            UsefulUtilities.LOGGER.error("MonsterHTTP failed to stop");
        }
    }
}