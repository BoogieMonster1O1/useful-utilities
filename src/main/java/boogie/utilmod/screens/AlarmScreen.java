package boogie.utilmod.screens;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.ClientCottonScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.round;

public class AlarmScreen extends LightweightGuiDescription {

    public AlarmScreen(){
        Timer timer = new Timer();

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256,160);

        WSprite clock = new WSprite(new Identifier("minecraft:textures/item/clock_00.png"));
        root.add(clock,1,1,2,2);

        WLabel label = new WLabel(I18n.translate("gui.utilities.alarm"));
        root.add(label,4,1,7,1);

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
                MinecraftClient.getInstance().openScreen(new ClientCottonScreen(new UtilitiesScreen()));
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        MinecraftClient.getInstance().openScreen(new TimeOnlyScreen(new TranslatableText("gui.utilities.alarm"),new TimeUpScreen(finalTime)));
                        timer.cancel();
                    }
                }, timeS);
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        });

        root.validate(this);
    }

    public static class TimeUpScreen extends LightweightGuiDescription{
        @Override
        public void addPainters() {
            getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
        }
        TimeUpScreen(long seconds){
            seconds/=1000;

            WGridPanel panel = new WGridPanel();
            panel.setSize(256,64);
            setRootPanel(panel);
            WLabel head = new WLabel(I18n.translate("gui.utilities.alarm.alarm1") + seconds + " " + I18n.translate("gui.utilities.alarm.alarm2"));
            panel.add(head,1,1,10,1);

            panel.validate(this);
        }
    }

    public static class TimeOnlyScreen extends ClientCottonScreen {
        TimeOnlyScreen(Text title, GuiDescription description) {
            super(title,description);
        }
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }

}
