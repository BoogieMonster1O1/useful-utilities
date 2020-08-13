package io.github.boogiemonster1o1.usefulutilities.description;

import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;

import java.io.File;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

public class HttpServerDescription extends LightweightGuiDescription {
    public HttpServerDescription() throws RuntimeException {
        WGridPanel root = new WGridPanel();
        root.setSize(256, 128);
        setRootPanel(root);
        WButton visit = new WButton(new TranslatableText("gui.utilities.monsterhttp.visit"));
        root.add(visit, 1, 3, 12, 1);
        visit.setOnClick(() -> {
            MinecraftClient.getInstance().openScreen(new ConfirmChatLinkScreen((bl) -> {
                if (bl) {
                    Util.getOperatingSystem().open("http://localhost:80");
                }
                MinecraftClient.getInstance().openScreen(new CottonClientScreen(new TranslatableText("gui.utilities.monsterhttp"), this));
            }, "http://localhost:80", true));
        });

        WButton open = new WButton(new TranslatableText("gui.utilities.monsterhttp.open"));
        root.add(open, 1, 5, 12, 1);
        open.setOnClick(() -> {
            Util.getOperatingSystem().open(new File(MinecraftClient.getInstance().runDirectory + File.separator + "web.txt"));
        });

        root.validate(this);
    }
}
