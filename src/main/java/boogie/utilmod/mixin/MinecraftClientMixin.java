package boogie.utilmod.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static boogie.utilmod.screens.MonsterHttpScreen.MonsterHTTP.stopHttp;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "scheduleStop", at = @At("HEAD"))
    public void serverStop(CallbackInfo ci){
        stopHttp();
    }
}
