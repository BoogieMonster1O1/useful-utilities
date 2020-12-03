package io.github.boogiemonster1o1.usefulutilities.description.screen

import io.github.cottonmc.cotton.gui.client.{BackgroundPainter, LightweightGuiDescription}
import io.github.cottonmc.cotton.gui.widget.{WGridPanel, WLabel}
import net.minecraft.client.resource.language.I18n

class CpsOutputScreen(var cps: Double) extends LightweightGuiDescription {
	val root = new WGridPanel
	this.setRootPanel(root)
	root.setSize(128, 64)
	val message = new WLabel(I18n.translate("gui.utilities.cps.cpsis") + this.cps)
	root.add(message, 1, 1, 7, 1)
	root.validate(this)

	override def addPainters(): Unit = {
		this.getRootPanel.setBackgroundPainter(BackgroundPainter.VANILLA)
	}
}
