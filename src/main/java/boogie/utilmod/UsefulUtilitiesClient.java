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
import org.lwjgl.glfw.GLFW;

public class UsefulUtilitiesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Starting Useful Utlities...");
        FabricKeyBinding key = FabricKeyBinding.Builder.create(new Identifier("utilmod", "util_key"), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_U, "key.categories.misc").build();
        KeyBindingRegistry.INSTANCE.register(key);
        ClientTickCallback.EVENT.register(e ->
        {
            if(key.isPressed()) MinecraftClient.getInstance().openScreen(new CottonClientScreen(new UtilitiesScreen()));
        });
    }
}
