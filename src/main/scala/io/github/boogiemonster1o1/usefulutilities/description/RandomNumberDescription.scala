package io.github.boogiemonster1o1.usefulutilities.description

import io.github.boogiemonster1o1.usefulutilities.api.UtilityScreen
import io.github.cottonmc.cotton.gui.GuiDescription
import io.github.cottonmc.cotton.gui.client.BackgroundPainter
import io.github.cottonmc.cotton.gui.client.CottonClientScreen
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription
import io.github.cottonmc.cotton.gui.widget.WButton
import io.github.cottonmc.cotton.gui.widget.WGridPanel
import io.github.cottonmc.cotton.gui.widget.WLabel
import io.github.cottonmc.cotton.gui.widget.WTextField
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment
import java.util.Random
import java.util.concurrent.ThreadLocalRandom
import net.minecraft.client.MinecraftClient
import net.minecraft.client.resource.language.I18n
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText

class RandomNumberDescription() extends LightweightGuiDescription {
	{
		val rand = new Random
		val root = new WGridPanel
		setRootPanel(root)
		root.setSize(256, 196)
		val label = new WLabel(new TranslatableText("gui.utilities.random"))
		root.add(label, 4, 1, 7, 1)
		label.setHorizontalAlignment(HorizontalAlignment.CENTER)
		val range1 = new WTextField
		root.add(range1, 2, 3, 4, 1)
		range1.setSuggestion(new TranslatableText("gui.utilities.random.range1"))
		val range2 = new WTextField
		root.add(range2, 8, 3, 4, 1)
		range2.setSuggestion(new TranslatableText("gui.utilities.random.range2"))
		val roll = new WButton(new TranslatableText("gui.utilities.random.roll"))
		root.add(roll, 2, 5, 10, 1)
		roll.setOnClick(() => {
			try {
				val from = Math.abs(Math.floor(range1.getText.toDouble).round)
				val to = Math.abs(Math.floor(range2.getText.toDouble).round) + 1
				//noinspection DuplicatedCode
				if (from == to) MinecraftClient.getInstance.openScreen(new RandomNumberDescription.DiceRollScreen(new TranslatableText("gui.utilities.random.rolled0"), new RandomNumberDescription.DiceRollScreen.DiceHasRolledScreen(from)))
				else if (from > to) throw new Exception
				else {
					val dice = ThreadLocalRandom.current.nextLong(from, to)
					MinecraftClient.getInstance.openScreen(new RandomNumberDescription.DiceRollScreen(new TranslatableText("gui.utilities.random.rolled0"), new RandomNumberDescription.DiceRollScreen.DiceHasRolledScreen(dice)))
				}
			} catch {
				case exception: Exception =>
					exception.printStackTrace()
			}
		})
		root.validate(this)
	}

	override def addPainters(): Unit = {
		getRootPanel.setBackgroundPainter(BackgroundPainter.VANILLA)
	}
}

object RandomNumberDescription {
	private[description] object DiceRollScreen {
		private[description] class DiceHasRolledScreen private[description](val `val`: Long) extends LightweightGuiDescription {
			val panel = new WGridPanel
			setRootPanel(panel)
			panel.setSize(256, 64)
			val head = new WLabel(I18n.translate("gui.utilities.random.rolled1") + `val` + "!")
			panel.add(head, 1, 1, 8, 1)
			panel.validate(this)

			override def addPainters(): Unit = {
				getRootPanel.setBackgroundPainter(BackgroundPainter.VANILLA)
			}
		}
	}

	class DiceRollScreen (val titleText: Text, val desc: GuiDescription) extends CottonClientScreen(titleText, desc) {
		override def onClose(): Unit = {
			MinecraftClient.getInstance.openScreen(new UtilityScreen(new RandomNumberDescription))
		}
	}
}
