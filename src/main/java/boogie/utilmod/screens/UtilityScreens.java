package boogie.utilmod.screens;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.ClientCottonScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class UtilityScreens extends ClientCottonScreen {
    public UtilityScreens(GuiDescription description) {
        super(description);
    }

    public UtilityScreens(Text title, GuiDescription description){
        super(title,description);
    }

    @Override
    public void onClose() {
        MinecraftClient.getInstance().openScreen(new ClientCottonScreen(new UtilitiesScreen()));
    }
}
