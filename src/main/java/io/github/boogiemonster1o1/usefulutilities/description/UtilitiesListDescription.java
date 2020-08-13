package io.github.boogiemonster1o1.usefulutilities.description;

import io.github.boogiemonster1o1.usefulutilities.screen.ExpenseScreen;
import io.github.boogiemonster1o1.usefulutilities.screen.UtilityScreen;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.IOException;

@Environment(EnvType.CLIENT)
public class UtilitiesListDescription extends LightweightGuiDescription {
    public UtilitiesListDescription(){
        if (MinecraftClient.getInstance().player == null) {
            return;
        }
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
        calc.setOnClick(() -> MinecraftClient.getInstance().openScreen(new UtilityScreen(new CalculatorDescription())));

        WButton cps = new WButton(new TranslatableText("gui.utilities.cps"));
        cps.setEnabled(true);
        root.add(cps, 7, 3, 6, 1);
        cps.setOnClick(()-> MinecraftClient.getInstance().openScreen(new UtilityScreen(new ClicksPerSecondDescription())));

        WButton portal = new WButton(new TranslatableText("gui.utilities.portal"));
        portal.setEnabled(true);
        root.add(portal, 14, 3, 6, 1);
        portal.setOnClick(()-> MinecraftClient.getInstance().openScreen(new UtilityScreen(new PortalCoordinatesDescription())));

        WButton random = new WButton(new TranslatableText("gui.utilities.random"));
        random.setEnabled(true);
        root.add(random, 0, 5, 6, 1);
        random.setOnClick(()-> MinecraftClient.getInstance().openScreen(new UtilityScreen(new RandomNumberDescription())));

        WButton alarm = new WButton(new TranslatableText("gui.utilities.alarm"));
        alarm.setEnabled(true);
        alarm.setOnClick(() -> MinecraftClient.getInstance().openScreen(new UtilityScreen(new AlarmDescription())));
        root.add(alarm, 7, 5, 6, 1);

        WButton crash = new WButton(new TranslatableText("gui.utilities.crash"));
        crash.setEnabled(true);
        crash.setOnClick(() -> MinecraftClient.getInstance().openScreen(new UtilityScreen(new GameCrashersDescription())));
        root.add(crash, 14, 5, 6, 1);

        WButton worldseed = new WButton(new TranslatableText("gui.utilities.world"));
        worldseed.setEnabled(true);
        root.add(worldseed,0,7,6,1);
        worldseed.setOnClick(()-> MinecraftClient.getInstance().openScreen(new UtilityScreen(new WorldInfoDescription())));

        WButton keyboard = new WButton(new TranslatableText("gui.utilities.keyboard"));
        keyboard.setEnabled(true);
        root.add(keyboard,7,7,6,1);
        keyboard.setOnClick(()-> MinecraftClient.getInstance().openScreen(new PianoDescription.KeyboardMainScreen()));

        WButton expense = new WButton(new TranslatableText("gui.utilities.expense"));
        expense.setEnabled(true);
        root.add(expense,14,7,6,1);
        expense.setOnClick(()->{
            File initFIle = new File(MinecraftClient.getInstance().runDirectory.toString() + File.separator + "init.txt");
            if(!initFIle.exists()){
                expense.setOnClick(()-> {
                    try {
                        MinecraftClient.getInstance().openScreen(new ExpenseScreen());
                    } catch (IOException exemption) {
                        exemption.printStackTrace();
                    }
                });
            }
            else {
                try {
                    MinecraftClient.getInstance().openScreen(new UtilityScreen(new TranslatableText("gui.utilities.expense"),new ExpenseDescription(true)));
                } catch (IOException expectation) {
                    expectation.printStackTrace();
                }
            }
        });

        WButton arraySort = new WButton(new TranslatableText("gui.utilities.sort"));
        arraySort.setEnabled(true);
        root.add(arraySort,0,9,6,1);
        arraySort.setOnClick(()-> MinecraftClient.getInstance().openScreen(new UtilityScreen(new TranslatableText(""),new ArraySortDescription())));

        WButton httpServer = new WButton(new TranslatableText("gui.utilities.monsterhttp.button"));
        httpServer.setEnabled(true);
        root.add(httpServer,7,9,6,1);
        httpServer.setOnClick(()-> {
            try {
                MinecraftClient.getInstance().openScreen(new UtilityScreen(new TranslatableText("gui.utilities.monsterhttp"),new HttpServerDescription()));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

        WLabel label = new WLabel(new TranslatableText("gui.utilities.title"), 0x000000);
        label.setHorizontalAlignment(HorizontalAlignment.CENTER);
        root.add(label, 7, 1, 6, 1);

        root.validate(this);
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }

}
