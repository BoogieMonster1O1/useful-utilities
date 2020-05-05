package boogie.utilmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class UsefulUtilitiesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Starting Useful Utlities...");
        FabricKeyBinding key = FabricKeyBinding.Builder.create(new Identifier("utilmod", "open"), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_U, "Utilities").build();
        KeyBindingRegistry.INSTANCE.addCategory("Utilities");
        KeyBindingRegistry.INSTANCE.register(key);
    }
}
