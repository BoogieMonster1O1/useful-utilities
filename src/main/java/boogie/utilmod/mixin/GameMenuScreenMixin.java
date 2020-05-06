package boogie.utilmod.mixin;

import boogie.utilmod.screens.UtilitiesScreen;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "initWidgets", at = @At("RETURN"))
    public void lol(CallbackInfo ci){
        this.addButton(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 144 + -16, 98, 20, I18n.translate("gui.utilities"), (buttonWidgetx) -> {
            MinecraftClient.getInstance().openScreen(new CottonClientScreen(new UtilitiesScreen()));
        }));
    }
}
