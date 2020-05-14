package boogie.utilmod.screens;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.ClientCottonScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import static net.minecraft.client.MinecraftClient.getInstance;
import static org.lwjgl.glfw.GLFW.*;


@Environment(EnvType.CLIENT)
public class KeyboardScreen extends LightweightGuiDescription {

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }

    private KeyboardScreen(){

        WPlainPanel root = new WPlainPanel();
        root.setSize(306,204);
        setRootPanel(root);

        int xC = 10;
        int yC = 30;

        KeyboardWSprite MIDDLE_C = new KeyboardWSprite(0.7f,KeyColor.WHITE, new LiteralText("C"), new LiteralText("A"));
        KeyboardWSprite C_SHARP = new KeyboardWSprite(0.75f,KeyColor.BLACK, new LiteralText("C#"), new LiteralText("W"));
        KeyboardWSprite D = new KeyboardWSprite(0.8f,KeyColor.WHITE, new LiteralText("D"), new LiteralText("S"));
        KeyboardWSprite D_SHARP = new KeyboardWSprite(0.85f,KeyColor.BLACK, new LiteralText("D#"), new LiteralText("E"));
        KeyboardWSprite E = new KeyboardWSprite(0.9f,KeyColor.WHITE, new LiteralText("E"), new LiteralText("D"));
        KeyboardWSprite F = new KeyboardWSprite(0.95f,KeyColor.WHITE, new LiteralText("F"), new LiteralText("F"));
        KeyboardWSprite F_SHARP = new KeyboardWSprite(1.0f,KeyColor.BLACK, new LiteralText("F#"), new LiteralText("T"));
        KeyboardWSprite G = new KeyboardWSprite(1.06f,KeyColor.WHITE, new LiteralText("G"), new LiteralText("G"));
        KeyboardWSprite G_SHARP = new KeyboardWSprite(1.12f,KeyColor.BLACK, new LiteralText("G#"), new LiteralText("Y"));
        KeyboardWSprite A = new KeyboardWSprite(1.18f,KeyColor.WHITE, new LiteralText("A"), new LiteralText("H"));
        KeyboardWSprite A_SHARP = new KeyboardWSprite(1.24f,KeyColor.BLACK, new LiteralText("A#"), new LiteralText("U"));
        KeyboardWSprite B = new KeyboardWSprite(1.32f,KeyColor.WHITE, new LiteralText("B"), new LiteralText("J"));
        KeyboardWSprite UPPER_C = new KeyboardWSprite(1.4f,KeyColor.WHITE, new LiteralText("C"), new LiteralText("K"));

        root.add(MIDDLE_C,xC+10,yC+20,30,80);
        root.add(D,xC+40+3,yC+20,30,80);
        root.add(E,xC+70+6,yC+20,30,80);
        root.add(F,xC+100+9,yC+20,30,80);
        root.add(G,xC+130+12,yC+20,30,80);
        root.add(A,xC+160+15,yC+20,30,80);
        root.add(B,xC+190+18,yC+20,30,80);
        root.add(UPPER_C,xC+220+21,yC+20,30,80);

        root.add(C_SHARP,xC+29,yC+20,25,50);
        root.add(D_SHARP,xC+62,yC+20,25,50);
        root.add(F_SHARP,xC+128,yC+20,25,50);
        root.add(G_SHARP,xC+161,yC+20,25,50);
        root.add(A_SHARP,xC+194,yC+20,25,50);

        root.validate(this);
    }


    public static class KeyboardMainScreen extends ClientCottonScreen {

        public KeyboardMainScreen() {
            super(new TranslatableText("gui.utilities.keyboard"), new KeyboardScreen());
        }

        @Override
        public void onClose() {
            getInstance().openScreen(new ClientCottonScreen(new UtilitiesScreen()));
        }

        @Override
        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            assert getInstance().player != null;
            if (keyCode == GLFW_KEY_ESCAPE && this.shouldCloseOnEsc()) {
                this.onClose();
                return true;
            }
            else if (keyCode == GLFW_KEY_TAB) {
                boolean bl = !hasShiftDown();
                if (!this.changeFocus(bl)) {
                    this.changeFocus(bl);
                }
                return true;
            }
            else{
                if(keyCode == GLFW_KEY_A) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,0.7f);
                else if(keyCode == GLFW_KEY_W) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,0.75f);
                else if(keyCode == GLFW_KEY_S) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,0.8f);
                else if(keyCode == GLFW_KEY_E) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,0.85f);
                else if(keyCode == GLFW_KEY_D) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,0.9f);
                else if(keyCode == GLFW_KEY_F) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,0.95f);
                else if(keyCode == GLFW_KEY_T) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,1.0f);
                else if(keyCode == GLFW_KEY_G) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,1.06f);
                else if(keyCode == GLFW_KEY_Y) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,1.12f);
                else if(keyCode == GLFW_KEY_H) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,1.18f);
                else if(keyCode == GLFW_KEY_U) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,1.24f);
                else if(keyCode == GLFW_KEY_J) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,1.32f);
                else if(keyCode == GLFW_KEY_K) getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,1.4f);

                else return super.keyPressed(keyCode, scanCode, modifiers);

                return true;
            }
        }
    }

    public enum KeyColor{
        WHITE,BLACK
    }


    /*
    static class BetterKeyBinding extends FabricKeyBinding {

        protected BetterKeyBinding(Identifier id, InputUtil.Type type, int code, String category) {
            super(id, type, code, category);
        }

        @Override
        public void setPressed(boolean pressed) {
            super.setPressed(pressed);

            if (pressed && !wasPressed()) {
                // run "on press start" here
            }
            if (!pressed && wasPressed()) {
                // run "on key released" here
            }
        }
    }
    */

    static class KeyboardWSprite extends WWidget {

        private Identifier TEXTURE;
        private Text label;
        private Text keyLabel;
        private float pitch;
        private KeyColor keyColor;

        KeyboardWSprite(float a, KeyColor keycolor,Text text,Text keytext){

            this.pitch = a;
            if(keycolor == KeyColor.BLACK){
                setSize(25,50);
                this.TEXTURE = new Identifier("utilmod", "textures/black.png");
                this.label = text;
                this.keyColor = keycolor;
                this.keyLabel = keytext;
            }
            else if(keycolor == KeyColor.WHITE){
                setSize(30,80);
                this.TEXTURE = new Identifier("utilmod", "textures/white.png");
                this.label = text;
                this.keyColor = keycolor;
                this.keyLabel = keytext;
            }

        }


        @Environment(EnvType.CLIENT)
        @Override
        public void onClick(int x, int y, int button) {
            assert getInstance().player != null;
            getInstance().player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING,2.0f,this.pitch);
        }

        @Environment(EnvType.CLIENT)
        @Override
        public void paintBackground(int x, int y, int mouseX, int mouseY) {
            ScreenDrawing.rect(TEXTURE,x, y, width, height, 0xFFFFFFFF);
            if(this.keyColor == KeyColor.WHITE) {
                ScreenDrawing.drawString(this.label.asFormattedString(), x, y + 55, 0x000000);
                ScreenDrawing.drawString(this.keyLabel.asFormattedString(), x, y + 70, 0xFF0000);
            }
            else if(this.keyColor == KeyColor.BLACK){
                ScreenDrawing.drawString(this.label.asFormattedString(), x, y + 20, 0xFFFFFF);
                ScreenDrawing.drawString(this.keyLabel.asFormattedString(), x, y + 35, 0xFF0000);
            }
        }
    }
}
