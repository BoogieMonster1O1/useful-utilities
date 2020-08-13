package io.github.boogiemonster1o1.usefulutilities.http;

import java.io.File;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import net.minecraft.client.MinecraftClient;

public class MonsterHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {
        String response = "<html><h1>It Works!</h1></html>";
        try {
            File webFile = new File(MinecraftClient.getInstance().runDirectory + File.separator + "web.txt");
            if (!webFile.exists()) {
                webFile.createNewFile();
                Files.write(Paths.get(webFile.getPath()), response.getBytes(StandardCharsets.UTF_8));
            }
            response = Arrays.toString(Files.readAllLines(webFile.toPath()).toArray()).replace(",", "").replace("[", "").replace("]", "");
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            OutputStream writer = exchange.getResponseBody();
            writer.write(response.getBytes());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}