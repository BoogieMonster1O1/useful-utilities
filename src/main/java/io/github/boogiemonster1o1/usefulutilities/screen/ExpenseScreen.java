package io.github.boogiemonster1o1.usefulutilities.screen;

import io.github.boogiemonster1o1.usefulutilities.description.ExpenseDescription;
import io.github.boogiemonster1o1.usefulutilities.description.UtilitiesListDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;

import java.io.IOException;

import net.minecraft.text.TranslatableText;

import static net.minecraft.client.MinecraftClient.getInstance;

public class ExpenseScreen extends CottonClientScreen {
    public ExpenseScreen() throws IOException {
        super(new TranslatableText("gui.utilities.expense"), new ExpenseDescription());
    }

    @Override
    public void onClose() {
        getInstance().openScreen(new CottonClientScreen(new UtilitiesListDescription()));
    }
}