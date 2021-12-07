package io.github.boogiemonster1o1.usefulutilities.description.screen

import io.github.boogiemonster1o1.usefulutilities.description.{ExpenseDescription, UtilitiesListDescription}
import io.github.cottonmc.cotton.gui.client.CottonClientScreen
import net.minecraft.client.MinecraftClient
import net.minecraft.text.TranslatableText

import java.io.IOException

class ExpenseScreen @throws[IOException] extends CottonClientScreen(new TranslatableText("gui.utilities.expense"), new ExpenseDescription) {
	override def onClose(): Unit = {
		MinecraftClient.getInstance.setScreen(new CottonClientScreen(new UtilitiesListDescription))
	}
}
