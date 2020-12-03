package io.github.boogiemonster1o1.usefulutilities.description.screen

import io.github.boogiemonster1o1.usefulutilities.description.UtilitiesListDescription
import io.github.cottonmc.cotton.gui.GuiDescription
import io.github.cottonmc.cotton.gui.client.CottonClientScreen
import net.minecraft.client.MinecraftClient

class YourCPSIsScreen(val description: GuiDescription) extends CottonClientScreen(description) {
	override def onClose(): Unit = {
		MinecraftClient.getInstance.openScreen(new CottonClientScreen(new UtilitiesListDescription))
	}
}
