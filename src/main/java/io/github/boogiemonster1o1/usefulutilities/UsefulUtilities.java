package io.github.boogiemonster1o1.usefulutilities;

import io.github.boogiemonster1o1.usefulutilities.description.UtilitiesListDescription;
import io.github.boogiemonster1o1.usefulutilities.http.MonsterHttp;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class UsefulUtilities implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(UsefulUtilities.class);

    @Override
    public void onInitializeClient() {
        LOGGER.info(MarkerManager.getMarker("Useful Utilities"), "Starting Useful Utilities...");
        KeyBinding key = KeyBindingHelper.registerKeyBinding(new KeyBinding("gui.utilities", GLFW.GLFW_KEY_U, "key.categories.misc"));
        ClientTickEvents.END_CLIENT_TICK.register(event -> {
            if (key.isPressed())
                MinecraftClient.getInstance().openScreen(new CottonClientScreen(new UtilitiesListDescription()));
        });
        MonsterHttp.startServer();
    }
}
