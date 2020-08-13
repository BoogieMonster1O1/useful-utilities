package io.github.boogiemonster1o1.usefulutilities.mixin;

import io.github.boogiemonster1o1.usefulutilities.description.UtilitiesListDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class MixinGameMenuScreen extends Screen {
    MixinGameMenuScreen(Text title) {
        super(title);
    }

    @Inject(method = "initWidgets", at = @At("RETURN"))
    public void addWidget(CallbackInfo ci){
        this.addButton(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 144 + -16, 98, 20, new TranslatableText("gui.utilities"), (buttonWidgetx) -> {
            MinecraftClient.getInstance().openScreen(new CottonClientScreen(new UtilitiesListDescription()));
        }));
    }
}
