package io.github.boogiemonster1o1.usefulutilities.description

import io.github.cottonmc.cotton.gui.client.BackgroundPainter
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription
import io.github.cottonmc.cotton.gui.widget.WButton
import io.github.cottonmc.cotton.gui.widget.WGridPanel
import io.github.cottonmc.cotton.gui.widget.WLabel
import io.github.cottonmc.cotton.gui.widget.WTextField
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment
import net.minecraft.text.LiteralText
import net.minecraft.text.TranslatableText

class PortalCoordinatesDescription() extends LightweightGuiDescription {
	{
		val outputTitle = new WLabel(new TranslatableText("gui.utilities.portal.output"))
		outputTitle.setHorizontalAlignment(HorizontalAlignment.CENTER)
		val root = new WGridPanel
		setRootPanel(root)
		root.setSize(256, 196)
		val label = new WLabel(new TranslatableText("gui.utilities.portal"))
		root.add(label, 4, 1, 7, 1)
		label.setHorizontalAlignment(HorizontalAlignment.CENTER)
		val inputx = new WTextField
		root.add(inputx, 2, 3, 4, 1)
		inputx.setSuggestion("X")
		val inputz = new WTextField
		root.add(inputz, 8, 3, 4, 1)
		inputz.setSuggestion("Z")
		val outX = new WLabel("X: ")
		root.add(outX, 4, 10, 5, 1)
		val outZ = new WLabel("Z: ")
		root.add(outZ, 4, 11, 5, 1)
		val overworld = new WButton(new TranslatableText("gui.utilities.portal.overworld"))
		root.add(overworld, 2, 5, 10, 1)
		overworld.setOnClick(() => {
			try {
				var x = inputx.getText.toDouble
				x *= 8.0
				var z = inputz.getText.toDouble
				z *= 8.0
				outX.setText(new LiteralText("X: " + x))
				outZ.setText(new LiteralText("Z: " + z))
				root.add(outputTitle, 4, 9, 7, 1)
			} catch {
				case exception: NumberFormatException =>
					exception.printStackTrace()
			}
		})
		val nether = new WButton(new TranslatableText("gui.utilities.portal.nether"))
		root.add(nether, 2, 7, 10, 1)
		nether.setOnClick(() => {
			try {
				var x = inputx.getText.toDouble
				x /= 8.0
				var z = inputz.getText.toDouble
				z /= 8.0
				outX.setText(new LiteralText("X: " + x))
				outZ.setText(new LiteralText("Z: " + z))
				root.add(outputTitle, 4, 9, 7, 1)
			} catch {
				case exception: NumberFormatException =>
					exception.printStackTrace()
			}
		})
		root.validate(this)
	}

	override def addPainters(): Unit = {
		getRootPanel.setBackgroundPainter(BackgroundPainter.VANILLA)
	}
}
