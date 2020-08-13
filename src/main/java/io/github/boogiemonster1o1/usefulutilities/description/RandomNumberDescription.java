package io.github.boogiemonster1o1.usefulutilities.description;

import io.github.boogiemonster1o1.usefulutilities.screen.UtilityScreen;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;

import java.util.Random;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import static io.github.boogiemonster1o1.usefulutilities.description.RandomNumberDescription.DiceRollScreen.DiceHasRolledScreen;
import static java.lang.Math.*;
import static java.util.concurrent.ThreadLocalRandom.current;

public class RandomNumberDescription extends LightweightGuiDescription {
    public RandomNumberDescription() {
        Random rand = new Random();

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 196);

        WLabel label = new WLabel(new TranslatableText("gui.utilities.random"));
        root.add(label, 4, 1, 7, 1);
        label.setHorizontalAlignment(HorizontalAlignment.CENTER);

        WTextField range1 = new WTextField();
        root.add(range1, 2, 3, 4, 1);
        range1.setSuggestion(new TranslatableText("gui.utilities.random.range1"));

        WTextField range2 = new WTextField();
        root.add(range2, 8, 3, 4, 1);
        range2.setSuggestion(new TranslatableText("gui.utilities.random.range2"));

        WButton roll = new WButton(new TranslatableText("gui.utilities.random.roll"));
        root.add(roll, 2, 5, 10, 1);
        roll.setOnClick(() -> {
            try {
                long from = abs(round(floor(Double.parseDouble(range1.getText()))));
                long to = abs(round(floor(Double.parseDouble(range2.getText())))) + 1;
                if (from == to) {
                    MinecraftClient.getInstance().openScreen(new DiceRollScreen(new TranslatableText("gui.utilities.random.rolled0"), new DiceHasRolledScreen(from)));
                } else if (from > to) throw new Exception();
                else {
                    long dice = current().nextLong(from, to);
                    MinecraftClient.getInstance().openScreen(new DiceRollScreen(new TranslatableText("gui.utilities.random.rolled0"), new DiceHasRolledScreen(dice)));
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        root.validate(this);
    }


    static class DiceRollScreen extends CottonClientScreen {
        DiceRollScreen(Text title, GuiDescription description) {
            super(title, description);
        }

        @Override
        public void onClose() {
            MinecraftClient.getInstance().openScreen(new UtilityScreen(new RandomNumberDescription()));
        }

        static class DiceHasRolledScreen extends LightweightGuiDescription {
            DiceHasRolledScreen(long val) {
                WGridPanel panel = new WGridPanel();
                setRootPanel(panel);
                panel.setSize(256, 64);

                WLabel head = new WLabel(I18n.translate("gui.utilities.random.rolled1") + val + "!");
                panel.add(head, 1, 1, 8, 1);

                panel.validate(this);
            }

            @Override
            public void addPainters() {
                getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
            }
        }
    }


    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }
}
