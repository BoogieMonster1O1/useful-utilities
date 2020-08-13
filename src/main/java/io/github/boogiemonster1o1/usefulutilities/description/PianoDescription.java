package io.github.boogiemonster1o1.usefulutilities.description;

import io.github.boogiemonster1o1.usefulutilities.widget.PianoKeySprite;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class PianoDescription extends LightweightGuiDescription {
    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }

    private PianoDescription() {

        WPlainPanel root = new WPlainPanel();
        root.setSize(306, 204);
        setRootPanel(root);

        int xC = 10;
        int yC = 30;

        PianoKeySprite MIDDLE_C = new PianoKeySprite(0.7f, KeyColor.WHITE, new LiteralText("C"), new LiteralText("A"));
        PianoKeySprite C_SHARP = new PianoKeySprite(0.75f, KeyColor.BLACK, new LiteralText("C#"), new LiteralText("W"));
        PianoKeySprite D = new PianoKeySprite(0.8f, KeyColor.WHITE, new LiteralText("D"), new LiteralText("S"));
        PianoKeySprite D_SHARP = new PianoKeySprite(0.85f, KeyColor.BLACK, new LiteralText("D#"), new LiteralText("E"));
        PianoKeySprite E = new PianoKeySprite(0.9f, KeyColor.WHITE, new LiteralText("E"), new LiteralText("D"));
        PianoKeySprite F = new PianoKeySprite(0.95f, KeyColor.WHITE, new LiteralText("F"), new LiteralText("F"));
        PianoKeySprite F_SHARP = new PianoKeySprite(1.0f, KeyColor.BLACK, new LiteralText("F#"), new LiteralText("T"));
        PianoKeySprite G = new PianoKeySprite(1.06f, KeyColor.WHITE, new LiteralText("G"), new LiteralText("G"));
        PianoKeySprite G_SHARP = new PianoKeySprite(1.12f, KeyColor.BLACK, new LiteralText("G#"), new LiteralText("Y"));
        PianoKeySprite A = new PianoKeySprite(1.18f, KeyColor.WHITE, new LiteralText("A"), new LiteralText("H"));
        PianoKeySprite A_SHARP = new PianoKeySprite(1.24f, KeyColor.BLACK, new LiteralText("A#"), new LiteralText("U"));
        PianoKeySprite B = new PianoKeySprite(1.32f, KeyColor.WHITE, new LiteralText("B"), new LiteralText("J"));
        PianoKeySprite UPPER_C = new PianoKeySprite(1.4f, KeyColor.WHITE, new LiteralText("C"), new LiteralText("K"));

        root.add(MIDDLE_C, xC + 10, yC + 20, 30, 80);
        root.add(D, xC + 40 + 3, yC + 20, 30, 80);
        root.add(E, xC + 70 + 6, yC + 20, 30, 80);
        root.add(F, xC + 100 + 9, yC + 20, 30, 80);
        root.add(G, xC + 130 + 12, yC + 20, 30, 80);
        root.add(A, xC + 160 + 15, yC + 20, 30, 80);
        root.add(B, xC + 190 + 18, yC + 20, 30, 80);
        root.add(UPPER_C, xC + 220 + 21, yC + 20, 30, 80);

        root.add(C_SHARP, xC + 29, yC + 20, 25, 50);
        root.add(D_SHARP, xC + 62, yC + 20, 25, 50);
        root.add(F_SHARP, xC + 128, yC + 20, 25, 50);
        root.add(G_SHARP, xC + 161, yC + 20, 25, 50);
        root.add(A_SHARP, xC + 194, yC + 20, 25, 50);

        root.validate(this);
    }


    public static class KeyboardMainScreen extends CottonClientScreen {
        public KeyboardMainScreen() {
            super(new TranslatableText("gui.utilities.keyboard"), new PianoDescription());
        }

        @Override
        public void onClose() {
            MinecraftClient.getInstance().openScreen(new CottonClientScreen(new UtilitiesListDescription()));
        }

        @Override
        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            assert MinecraftClient.getInstance().player != null;
            if (keyCode == GLFW.GLFW_KEY_ESCAPE && this.shouldCloseOnEsc()) {
                this.onClose();
                return true;
            } else if (keyCode == GLFW.GLFW_KEY_TAB) {
                boolean bl = !hasShiftDown();
                if (!this.changeFocus(bl)) {
                    this.changeFocus(bl);
                }
                return true;
            } else {
                if (keyCode == GLFW.GLFW_KEY_A)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.7f);
                else if (keyCode == GLFW.GLFW_KEY_W)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.75f);
                else if (keyCode == GLFW.GLFW_KEY_S)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.8f);
                else if (keyCode == GLFW.GLFW_KEY_E)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.85f);
                else if (keyCode == GLFW.GLFW_KEY_D)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.9f);
                else if (keyCode == GLFW.GLFW_KEY_F)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.95f);
                else if (keyCode == GLFW.GLFW_KEY_T)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.0f);
                else if (keyCode == GLFW.GLFW_KEY_G)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.06f);
                else if (keyCode == GLFW.GLFW_KEY_Y)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.12f);
                else if (keyCode == GLFW.GLFW_KEY_H)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.18f);
                else if (keyCode == GLFW.GLFW_KEY_U)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.24f);
                else if (keyCode == GLFW.GLFW_KEY_J)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.32f);
                else if (keyCode == GLFW.GLFW_KEY_K)
                    MinecraftClient.getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.4f);

                else return super.keyPressed(keyCode, scanCode, modifiers);

                return true;
            }
        }
    }

    public enum KeyColor {
        WHITE,
        BLACK
    }
}
