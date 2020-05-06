package boogie.utilmod.screens;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.text.TranslatableText;

public class PortalCoordinatesScreen extends LightweightGuiDescription {

    public PortalCoordinatesScreen(){

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256,256);

        WLabel label = new WLabel(new TranslatableText("gui.utilities.portal"));
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }
}
