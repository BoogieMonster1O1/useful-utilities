package io.github.boogiemonster1o1.usefulutilities.widget;

import io.github.boogiemonster1o1.usefulutilities.description.PianoDescription;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class PianoKeySprite extends WWidget {
    private Identifier texture;
    private Text label;
    private Text keyLabel;
    private final HorizontalAlignment alignment = HorizontalAlignment.CENTER;
    private final float pitch;
    private PianoDescription.KeyColor keyColor;

    public PianoKeySprite(float a, PianoDescription.KeyColor keycolor, Text text, Text keytext) {
        this.pitch = a;
        if (keycolor == PianoDescription.KeyColor.BLACK) {
            setSize(25, 50);
            this.texture = new Identifier("utilmod", "textures/black.png");
            this.label = text;
            this.keyColor = keycolor;
            this.keyLabel = keytext;
        } else if (keycolor == PianoDescription.KeyColor.WHITE) {
            setSize(30, 80);
            this.texture = new Identifier("utilmod", "textures/white.png");
            this.label = text;
            this.keyColor = keycolor;
            this.keyLabel = keytext;
        }

    }


    @Environment(EnvType.CLIENT)
    @Override
    public void onClick(int x, int y, int button) {
        if (MinecraftClient.getInstance().player == null) {
            return;
        }
        MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, this.pitch);
    }

    @Override
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        super.paint(matrices, x, y, mouseX, mouseY);
        ScreenDrawing.texturedRect(x, y, width, height, texture, 0xFFFFFFFF);
        if (this.keyColor == PianoDescription.KeyColor.WHITE) {
            ScreenDrawing.drawString(matrices, this.label.asString(), this.alignment, x, y + 55, this.width, 0x000000);
            ScreenDrawing.drawString(matrices, this.keyLabel.asString(), this.alignment, x, y + 70, this.width, 0xFF0000);
        } else if (this.keyColor == PianoDescription.KeyColor.BLACK) {
            ScreenDrawing.drawString(matrices, this.label.asString(), this.alignment, x, y + 20, this.width, 0xFFFFFF);
            ScreenDrawing.drawString(matrices, this.keyLabel.asString(), this.alignment, x, y + 35, this.width, 0xFF0000);
        }
    }
}