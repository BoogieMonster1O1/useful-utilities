package io.github.boogiemonster1o1.usefulutilities.description;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import static java.lang.Math.round;

public class AlarmDescription extends LightweightGuiDescription {

    public AlarmDescription(){
        Timer timer = new Timer();

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256,160);

        WSprite clock = new WSprite(new Identifier("minecraft:textures/item/clock_00.png"));
        root.add(clock,1,1,2,2);

        WLabel label = new WLabel(new TranslatableText("gui.utilities.alarm"));
        root.add(label,4,1,7,1);
        label.setHorizontalAlignment(HorizontalAlignment.CENTER);

        WTextField time = new WTextField();
        time.setSuggestion(new TranslatableText("gui.utilities.alarm.time"));
        root.add(time,4,3,6,1);

        WButton set = new WButton(new TranslatableText("gui.utilities.alarm.set"));
        set.setEnabled(true);
        root.add(set,3,5,8,1);
        set.setOnClick(()->{
            try{
                long timeS = round(Double.parseDouble(time.getText()));
                timeS = timeS * 1000;
                long finalTime = timeS;
                MinecraftClient.getInstance().openScreen(new CottonClientScreen(new UtilitiesListDescription()));
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        MinecraftClient.getInstance().openScreen(new CottonClientScreen(new TranslatableText("gui.utilities.alarm"),new TimeUpDescription(finalTime)));
                        timer.cancel();
                    }
                }, timeS);
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        });

        root.validate(this);
    }

    public static class TimeUpDescription extends LightweightGuiDescription {
        @Override
        public void addPainters() {
            getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
        }
        TimeUpDescription(long seconds){
            seconds/=1000;

            WGridPanel panel = new WGridPanel();
            panel.setSize(256,64);
            setRootPanel(panel);
            WLabel head = new WLabel(I18n.translate("gui.utilities.alarm.alarm1") + seconds + " " + I18n.translate("gui.utilities.alarm.alarm2"));
            head.setHorizontalAlignment(HorizontalAlignment.CENTER);
            panel.add(head,1,1,10,1);

            panel.validate(this);
        }
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }

}
