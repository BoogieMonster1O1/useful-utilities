package boogie.utilmod.screens;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import static boogie.utilmod.UsefulUtilitiesClient.*;

public class MonsterHttpScreen extends LightweightGuiDescription {
    public MonsterHttpScreen(){
        WGridPanel root = new WGridPanel();
        root.setSize(256,256);
        setRootPanel(root);
        root.validate(this);
    }

    public static class MonsterHTTP {
        private HttpServer server;
        protected boolean on;
        public void start() {
            try {
                this.server = HttpServer.create(new InetSocketAddress(80), 0);
                this.server.createContext("/", new Handler());
                this.server.setExecutor(null);
                this.server.start();
                LOGGER.info("MonsterHTTP started on port 80");
                on = true;
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("MonsterHTTP failed to start");
                on = false;
            }
        }
        public void stop(){
            try{
                this.server.stop(0);
                LOGGER.info("MonsterHTTP stopped");
                on = false;
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("MonsterHTTP failed to stop");
                on = true;
            }
        }
    }
    public static class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
            String response = "<html><h1>Response</h1></html>";
            try {
                exchange.sendResponseHeaders(200,response.getBytes().length);
                OutputStream writer = exchange.getResponseBody();
                writer.write(response.getBytes());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
