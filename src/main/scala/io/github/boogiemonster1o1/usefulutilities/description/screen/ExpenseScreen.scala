package io.github.boogiemonster1o1.usefulutilities.description.screen

import io.github.boogiemonster1o1.usefulutilities.description.ExpenseDescription
import io.github.boogiemonster1o1.usefulutilities.description.UtilitiesListDescription
import io.github.cottonmc.cotton.gui.client.CottonClientScreen
import java.io.IOException
import net.minecraft.client.MinecraftClient
import net.minecraft.text.TranslatableText

class ExpenseScreen @throws[IOException] extends CottonClientScreen(new TranslatableText("gui.utilities.expense"), new ExpenseDescription) {
	override def onClose(): Unit = {
		MinecraftClient.getInstance.openScreen(new CottonClientScreen(new UtilitiesListDescription))
	}
}
