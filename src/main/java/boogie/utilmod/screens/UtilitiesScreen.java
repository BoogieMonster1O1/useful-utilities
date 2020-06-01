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

import java.io.File;
import java.io.IOException;

import static boogie.utilmod.screens.KeyboardScreen.KeyboardMainScreen;
import static net.minecraft.client.MinecraftClient.getInstance;
import static boogie.utilmod.screens.ExpenseScreen.ExpenseMainScreen;

@Environment(EnvType.CLIENT)
public class UtilitiesScreen extends LightweightGuiDescription {
    public UtilitiesScreen(){
        assert getInstance().player != null;
        boolean cheats = getInstance().player.world.getLevelProperties().areCommandsAllowed();
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
        calc.setOnClick(() -> getInstance().openScreen(new UtilityScreens(new CalculatorScreen())));

        WButton cps = new WButton(new TranslatableText("gui.utilities.cps"));
        cps.setEnabled(true);
        root.add(cps, 7, 3, 6, 1);
        cps.setOnClick(()-> getInstance().openScreen(new UtilityScreens(new ClicksPerSecondScreen())));

        WButton portal = new WButton(new TranslatableText("gui.utilities.portal"));
        portal.setEnabled(true);
        root.add(portal, 14, 3, 6, 1);
        portal.setOnClick(()-> getInstance().openScreen(new UtilityScreens(new PortalCoordinatesScreen())));

        WButton random = new WButton(new TranslatableText("gui.utilities.random"));
        random.setEnabled(true);
        root.add(random, 0, 5, 6, 1);
        random.setOnClick(()-> getInstance().openScreen(new UtilityScreens(new DiceOnD3mandScreen())));

        WButton alarm = new WButton(new TranslatableText("gui.utilities.alarm"));
        alarm.setEnabled(true);
        alarm.setOnClick(() -> getInstance().openScreen(new UtilityScreens(new AlarmScreen())));
        root.add(alarm, 7, 5, 6, 1);

        WButton crash = new WButton(new TranslatableText("gui.utilities.crash"));
        crash.setEnabled(true);
        crash.setOnClick(() -> getInstance().openScreen(new UtilityScreens(new GameCrashersScreen())));
        root.add(crash, 14, 5, 6, 1);

        WButton worldseed = new WButton(new TranslatableText("gui.utilities.world"));
        worldseed.setEnabled(true);
        root.add(worldseed,0,7,6,1);
        worldseed.setOnClick(()-> getInstance().openScreen(new UtilityScreens(new WorldInfoScreen())));

        WButton keyboard = new WButton(new TranslatableText("gui.utilities.keyboard"));
        keyboard.setEnabled(true);
        root.add(keyboard,7,7,6,1);
        keyboard.setOnClick(()-> getInstance().openScreen(new KeyboardMainScreen()));

        WButton expense = new WButton(new TranslatableText("gui.utilities.expense"));
        expense.setEnabled(true);
        root.add(expense,14,7,6,1);
        expense.setOnClick(()->{
            File initFIle = new File(getInstance().runDirectory.toString() + File.separator + "init.txt");
            if(!initFIle.exists()){
                expense.setOnClick(()-> {
                    try {
                        getInstance().openScreen(new ExpenseMainScreen());
                    } catch (IOException exemption) {
                        exemption.printStackTrace();
                    }
                });
            }
            else {
                try {
                    getInstance().openScreen(new UtilityScreens(new TranslatableText("gui.utilities.expense"),new ExpenseScreen(true)));
                } catch (IOException expectation) {
                    expectation.printStackTrace();
                }
            }
        });

        WButton arraySort = new WButton(new TranslatableText("gui.utilities.sort"));
        arraySort.setEnabled(true);
        root.add(arraySort,0,9,6,1);
        arraySort.setOnClick(()-> getInstance().openScreen(new UtilityScreens(new TranslatableText(""),new ArraySortScreen())));

        WButton httpServer = new WButton(new TranslatableText("gui.utilities.monsterhttp.button"));
        httpServer.setEnabled(true);
        root.add(httpServer,7,9,6,1);
        httpServer.setOnClick(()-> {
            try {
                getInstance().openScreen(new UtilityScreens(new TranslatableText("gui.utilities.monsterhttp"),new MonsterHttpScreen()));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

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
