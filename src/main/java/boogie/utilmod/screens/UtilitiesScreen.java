package boogie.utilmod.screens;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.data.Alignment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class UtilitiesScreen extends LightweightGuiDescription {

    public UtilitiesScreen(){
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(356, 240);

        WSprite icon = new WSprite(new Identifier("minecraft:textures/block/furnace_front_on.png"));
        root.add(icon, 0, 2, 1, 1);

        WButton calc = new WButton(new TranslatableText("gui.utilities.calculator"));
        root.add(calc, 0, 3, 4, 1);
        WButton cps = new WButton(new TranslatableText("gui.utilities.cps"));
        root.add(cps, 0, 5, 4, 1);

        WLabel label = new WLabel(new TranslatableText("gui.utilities.title"), 0x000000);
        label.setAlignment(Alignment.CENTER);
        root.add(label, 2, 2, 2, 1);

        root.validate(this);
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }

}
