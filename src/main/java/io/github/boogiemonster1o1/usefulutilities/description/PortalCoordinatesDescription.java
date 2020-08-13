package io.github.boogiemonster1o1.usefulutilities.description;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;

import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class PortalCoordinatesDescription extends LightweightGuiDescription {
    public PortalCoordinatesDescription() {
        WLabel outputTitle = new WLabel(new TranslatableText("gui.utilities.portal.output"));
        outputTitle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256,196);

        WLabel label = new WLabel(new TranslatableText("gui.utilities.portal"));
        root.add(label,4,1,7,1);
        label.setHorizontalAlignment(HorizontalAlignment.CENTER);

        WTextField inputx = new WTextField();
        root.add(inputx,2,3,4,1);
        inputx.setSuggestion("X");

        WTextField inputz = new WTextField();
        root.add(inputz, 8,3,4,1);
        inputz.setSuggestion("Z");

        WLabel outX = new WLabel("X: ");
        root.add(outX, 4,10,5,1);

        WLabel outZ = new WLabel("Z: ");
        root.add(outZ, 4,11,5,1);

        WButton overworld = new WButton(new TranslatableText("gui.utilities.portal.overworld"));
        root.add(overworld,2,5,10,1);
        overworld.setOnClick(() -> {
            try{
                double x = Double.parseDouble(inputx.getText());
                x*=8.0;
                double z = Double.parseDouble(inputz.getText());
                z*=8.0;
                outX.setText(new LiteralText("X: " + x));
                outZ.setText(new LiteralText("Z: " + z));
                root.add(outputTitle,4,9,7,1);
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        });

        WButton nether = new WButton(new TranslatableText("gui.utilities.portal.nether"));
        root.add(nether,2,7,10,1);
        nether.setOnClick(()->{
            try{
                double x = Double.parseDouble(inputx.getText());
                x/=8.0;
                double z = Double.parseDouble(inputz.getText());
                z/=8.0;
                outX.setText(new LiteralText("X: " + x));
                outZ.setText(new LiteralText("Z: " + z));
                root.add(outputTitle,4,9,7,1);
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        });

        root.validate(this);
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }
}
