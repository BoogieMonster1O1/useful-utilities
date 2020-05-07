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
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.lwjgl.openal.AL;

@Environment(EnvType.CLIENT)
public class UtilitiesScreen extends LightweightGuiDescription {

    public UtilitiesScreen(){
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(356, 240);

        WSprite left_icon = new WSprite(new Identifier("minecraft:textures/block/furnace_front_on.png"));
        root.add(left_icon, 0, 0, 2, 2);

        WSprite right_icon = new WSprite(new Identifier("minecraft:textures/block/crafting_table_side.png"));
        root.add(right_icon, 18, 0, 2, 2);




        WButton calc = new WButton(new TranslatableText("gui.utilities.calculator"));
        calc.setEnabled(true);
        root.add(calc, 0, 3, 6, 1);
        calc.setOnClick(() -> MinecraftClient.getInstance().openScreen(new UtilityScreens(new CalculatorScreen())));

        WButton cps = new WButton(new TranslatableText("gui.utilities.cps"));
        cps.setEnabled(true);
        root.add(cps, 7, 3, 6, 1);
        cps.setOnClick(()-> MinecraftClient.getInstance().openScreen(new UtilityScreens(new ClicksPerSecondScreen())));

        WButton portal = new WButton(new TranslatableText("gui.utilities.portal"));
        portal.setEnabled(true);
        root.add(portal, 14, 3, 6, 1);
        portal.setOnClick(()-> MinecraftClient.getInstance().openScreen(new UtilityScreens(new PortalCoordinatesScreen())));

        WButton random = new WButton(new TranslatableText("gui.utilities.random"));
        random.setEnabled(true);
        root.add(random, 0, 5, 6, 1);
        random.setOnClick(()-> MinecraftClient.getInstance().openScreen(new UtilityScreens(new DiceOnD3mandScreen())));

        WButton alarm = new WButton(new TranslatableText("gui.utilities.alarm"));
        alarm.setEnabled(true);
        alarm.setOnClick(() -> MinecraftClient.getInstance().openScreen(new UtilityScreens(new AlarmScreen())));
        root.add(alarm, 7, 5, 6, 1);

        WButton crash = new WButton(new TranslatableText("gui.utilities.crash"));
        crash.setEnabled(true);
        crash.setOnClick(() -> MinecraftClient.getInstance().openScreen(new UtilityScreens(new GameCrashersScreen())));
        root.add(crash, 14, 5, 6, 1);

        WButton worldseed = new WButton(new TranslatableText("gui.utilities.world"));
        worldseed.setEnabled(true);
        root.add(worldseed,0,7,6,1);
        worldseed.setOnClick(()-> MinecraftClient.getInstance().openScreen(new UtilityScreens(new WorldInfoScreen())));

        WButton grules = new WButton(new TranslatableText("gui.utilities.gamerules"));
        grules.setEnabled(true);
        root.add(grules,7,7,6,1);



        WLabel label = new WLabel(new TranslatableText("gui.utilities.title"), 0x000000);
        label.setAlignment(Alignment.CENTER);
        root.add(label, 7, 1, 6, 1);

        root.validate(this);
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }

}
