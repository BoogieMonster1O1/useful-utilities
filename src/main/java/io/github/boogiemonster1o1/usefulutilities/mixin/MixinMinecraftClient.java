package io.github.boogiemonster1o1.usefulutilities.mixin;

import io.github.boogiemonster1o1.usefulutilities.http.MonsterHttp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "scheduleStop", at = @At("HEAD"))
    public void serverStop(CallbackInfo ci){
        MonsterHttp.stopServer();
    }
}
