package boogie.utilmod;

import boogie.utilmod.screens.UtilitiesScreen;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

import static org.apache.logging.log4j.LogManager.getLogger;

public class UsefulUtilitiesClient implements ClientModInitializer {
    public static final Logger LOGGER = getLogger(UsefulUtilitiesClient.class);
    @Override
    public void onInitializeClient() {
        LOGGER.info(MarkerManager.getMarker("Useful Utilities"),"Starting Useful Utilities...");
        FabricKeyBinding key = FabricKeyBinding.Builder.create(new Identifier("utilmod", "util_key"), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_U, "key.categories.misc").build();
        KeyBindingRegistry.INSTANCE.register(key);
        ClientTickCallback.EVENT.register(event ->
        {
            if(key.isPressed()) MinecraftClient.getInstance().openScreen(new CottonClientScreen(new UtilitiesScreen()));
        });
    }
}
