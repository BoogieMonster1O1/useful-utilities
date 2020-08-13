package io.github.boogiemonster1o1.usefulutilities.description;

import io.github.boogiemonster1o1.usefulutilities.screen.YourCPSIsScreen;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WLabeledSlider;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class ClicksPerSecondDescription extends LightweightGuiDescription {
    private boolean begin = false;
    private double cps = 0;
    private double clicks = 0;
    private int time = 0;
    private Timer timer = null;

    public ClicksPerSecondDescription() {

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(160, 160);
        root.validate(this);

        WLabel label = new WLabel(new TranslatableText("gui.utilities.cps"));
        label.setHorizontalAlignment(HorizontalAlignment.CENTER);
        root.add(label, 1, 1, 7, 1);

        WLabeledSlider seconds = new WLabeledSlider(5, 30, Axis.HORIZONTAL);
        seconds.setLabel(new LiteralText(I18n.translate("gui.utilities.cps.time") + seconds.getValue()));
        seconds.setLabelUpdater(i -> new LiteralText(I18n.translate("gui.utilities.cps.time") + seconds.getValue()));
        root.add(seconds, 1, 5, 7, 1);

        WButton click = new WButton(new TranslatableText("gui.utilities.cps.click"));
        root.add(click, 1, 3, 7, 1);
        click.setOnClick(() -> {
            if (!begin) {
                begin = true;
                timer = new Timer();
                time = seconds.getValue();
                root.remove(seconds);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        cps = clicks / time;
                        MinecraftClient.getInstance().openScreen(new YourCPSIsScreen(new YourCPSIsScreen.CPSOutputScreen(cps)));
                        timer.cancel();
                        timer = null;
                        root.add(seconds, 1, 5, 7, 1);
                        begin = false;
                        time = 0;
                        clicks = 0;
                    }
                }, time * 1000);
            }
            clicks += 1;
        });

        root.validate(this);
    }


    @Override
    public void addPainters() {
        this.getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }
}
