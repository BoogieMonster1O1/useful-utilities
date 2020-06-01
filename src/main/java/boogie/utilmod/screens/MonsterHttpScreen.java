package boogie.utilmod.screens;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import static boogie.utilmod.UsefulUtilitiesClient.LOGGER;
import static net.minecraft.client.MinecraftClient.getInstance;

public class MonsterHttpScreen extends LightweightGuiDescription {


    public MonsterHttpScreen() throws Throwable{
        WGridPanel root = new WGridPanel();
        root.setSize(256,128);
        setRootPanel(root);
        WLabel status;
        if(InetAddress.getByName("localhost:80").isReachable(5000)) {
            status = new WLabel(new TranslatableText("gui.utilities.monsterhttp.online"));
            WButton visit = new WButton(new TranslatableText("gui.utilities.monsterhttp.visit"));
            root.add(visit,2,3,10,1);
            getInstance().openScreen(new ConfirmChatLinkScreen((bl)->{
                if (bl) {
                    Util.getOperatingSystem().open("localhost");
                }
            },"localhost",true));
        }
        else status = new WLabel(new TranslatableText("gui.utilities.monsterhttp.offline"));

        root.validate(this);
        root.add(status,2,1,10,1);
    }
    public static class MonsterHTTP {
        private static HttpServer server;
        public static void start() {
            try {
                server = HttpServer.create(new InetSocketAddress(80), 0);
                server.createContext("/", new Handler());
                server.setExecutor(null);
                server.start();
                LOGGER.info("MonsterHTTP started on port 80");
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("MonsterHTTP failed to start");
            }
        }
        public static void stopHttp(){
            try{
                server.stop(0);
                LOGGER.info("MonsterHTTP stopped");
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("MonsterHTTP failed to stop");
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
