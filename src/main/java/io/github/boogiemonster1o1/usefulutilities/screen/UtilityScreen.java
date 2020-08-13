package io.github.boogiemonster1o1.usefulutilities.screen;

import io.github.boogiemonster1o1.usefulutilities.description.UtilitiesListDescription;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class UtilityScreen extends CottonClientScreen {
    public UtilityScreen(GuiDescription description) {
        super(description);
    }

    public UtilityScreen(Text title, GuiDescription description){
        super(title,description);
    }

    @Override
    public void onClose() {
        MinecraftClient.getInstance().openScreen(new CottonClientScreen(new UtilitiesListDescription()));
    }
}
