package io.github.boogiemonster1o1.usefulutilities.description.screen;

import io.github.boogiemonster1o1.usefulutilities.description.UtilitiesListDescription;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;

public class YourCPSIsScreen extends CottonClientScreen {
    public YourCPSIsScreen(GuiDescription description) {
        super(description);
    }

    @Override
    public void onClose() {
        MinecraftClient.getInstance().openScreen(new CottonClientScreen(new UtilitiesListDescription()));
    }

    public static class CPSOutputScreen extends LightweightGuiDescription {
        double cps = 0;

        public CPSOutputScreen(double a) {
            cps = a;
            WGridPanel root = new WGridPanel();
            setRootPanel(root);
            root.setSize(128, 64);
            WLabel message = new WLabel(I18n.translate("gui.utilities.cps.cpsis") + cps);
            root.add(message, 1, 1, 7, 1);

            root.validate(this);
        }

        @Override
        public void addPainters() {
            getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
        }
    }
}
