package io.github.boogiemonster1o1.usefulutilities.api;

import io.github.cottonmc.cotton.gui.widget.WButton;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import net.fabricmc.loader.api.FabricLoader;

public class UtilityManager {
    private int utilitiesCount;
    private final Set<WButton> buttons = Sets.newHashSet();
    private static UtilityManager instance = null;

    public int getUtilitiesCount() {
        return utilitiesCount;
    }

    private UtilityManager() {
        this.utilitiesCount = 0;
    }

    public void addUtility(UtilityScreen screen, Text label) {
        WButton button = new WButton();
        button.setOnClick(() -> MinecraftClient.getInstance().openScreen(screen));
        button.setLabel(label);
        buttons.add(button);
        utilitiesCount++;
    }

    public static void execute() {
        instance = new UtilityManager();
        FabricLoader.getInstance().getEntrypoints("usefulutilities", UsefulUtilitiesApi.class).forEach((api) -> api.initializeUtilities(instance));
    }

    public Set<WButton> getButtons() {
        return buttons;
    }

    public static UtilityManager getInstance() {
        return instance;
    }
}
