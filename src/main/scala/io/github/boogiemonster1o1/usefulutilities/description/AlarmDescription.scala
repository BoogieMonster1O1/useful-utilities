package io.github.boogiemonster1o1.usefulutilities.description

import io.github.cottonmc.cotton.gui.client.BackgroundPainter
import io.github.cottonmc.cotton.gui.client.CottonClientScreen
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription
import io.github.cottonmc.cotton.gui.widget.WButton
import io.github.cottonmc.cotton.gui.widget.WGridPanel
import io.github.cottonmc.cotton.gui.widget.WLabel
import io.github.cottonmc.cotton.gui.widget.WSprite
import io.github.cottonmc.cotton.gui.widget.WTextField
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment
import java.util.Timer
import java.util.TimerTask
import net.minecraft.client.MinecraftClient
import net.minecraft.client.resource.language.I18n
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier

object AlarmDescription {
	class TimeUpDescription private[description](var seconds: Long) extends LightweightGuiDescription {
		{
			seconds /= 1000
			val panel = new WGridPanel
			panel.setSize(256, 64)
			setRootPanel(panel)
			val head = new WLabel(I18n.translate("gui.utilities.alarm.alarm1") + seconds + " " + I18n.translate("gui.utilities.alarm.alarm2"))
			head.setHorizontalAlignment(HorizontalAlignment.CENTER)
			panel.add(head, 1, 1, 10, 1)
			panel.validate(this)
		}

		override def addPainters(): Unit = {
			getRootPanel.setBackgroundPainter(BackgroundPainter.VANILLA)
		}
	}
}

class AlarmDescription() extends LightweightGuiDescription {
	{
		val timer = new Timer
		val root = new WGridPanel
		setRootPanel(root)
		root.setSize(256, 160)
		val clock = new WSprite(new Identifier("minecraft:textures/item/clock_00.png"))
		root.add(clock, 1, 1, 2, 2)
		val label = new WLabel(new TranslatableText("gui.utilities.alarm"))
		root.add(label, 4, 1, 7, 1)
		label.setHorizontalAlignment(HorizontalAlignment.CENTER)
		val time = new WTextField
		time.setSuggestion(new TranslatableText("gui.utilities.alarm.time"))
		root.add(time, 4, 3, 6, 1)
		val set = new WButton(new TranslatableText("gui.utilities.alarm.set"))
		set.setEnabled(true)
		root.add(set, 3, 5, 8, 1)
		set.setOnClick(() => {
			try {
				var timeS = time.getText.toDouble.round
				timeS = timeS * 1000
				val finalTime = timeS
				MinecraftClient.getInstance.setScreen(new CottonClientScreen(new UtilitiesListDescription))
				timer.schedule(new TimerTask() {
					override def run(): Unit = {
						MinecraftClient.getInstance().execute(() => {
							MinecraftClient.getInstance.setScreen(new CottonClientScreen(new TranslatableText("gui.utilities.alarm"), new AlarmDescription.TimeUpDescription(finalTime)))
							timer.cancel()
						})
					}
				}, timeS)
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
