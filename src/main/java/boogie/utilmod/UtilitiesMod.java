package boogie.utilmod;

import net.fabricmc.api.ModInitializer;

public class UtilitiesMod implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("\nNote: Useful Utilities runs only on the client\n Do not try running the mod on servers");
    }
}
