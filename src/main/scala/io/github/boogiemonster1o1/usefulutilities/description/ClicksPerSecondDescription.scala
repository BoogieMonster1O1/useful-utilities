package io.github.boogiemonster1o1.usefulutilities.description

import io.github.boogiemonster1o1.usefulutilities.description.screen.{CpsOutputScreen, YourCPSIsScreen}
import io.github.cottonmc.cotton.gui.client.{BackgroundPainter, LightweightGuiDescription}
import io.github.cottonmc.cotton.gui.widget.{WButton, WGridPanel, WLabel, WLabeledSlider}
import io.github.cottonmc.cotton.gui.widget.data.{Axis, HorizontalAlignment}
import net.minecraft.client.MinecraftClient
import net.minecraft.client.resource.language.I18n
import net.minecraft.text.{LiteralText, TranslatableText}

import java.util.{Timer, TimerTask}

class ClicksPerSecondDescription() extends LightweightGuiDescription {
	private var begin = false
	private var cps = 0
	private var clicks = 0
	private var time = 0
	private var timer: Timer = _

	{
		val root = new WGridPanel
		setRootPanel(root)
		root.setSize(160, 160)
		root.validate(this)
		val label = new WLabel(new TranslatableText("gui.utilities.cps"))
		label.setHorizontalAlignment(HorizontalAlignment.CENTER)
		root.add(label, 1, 1, 7, 1)
		val seconds = new WLabeledSlider(5, 30, Axis.HORIZONTAL)
		seconds.setLabel(new LiteralText(I18n.translate("gui.utilities.cps.time") + seconds.getValue))
		seconds.setLabelUpdater((i: Int) => new LiteralText(I18n.translate("gui.utilities.cps.time") + seconds.getValue))
		root.add(seconds, 1, 5, 7, 1)
		val click = new WButton(new TranslatableText("gui.utilities.cps.click"))
		root.add(click, 1, 3, 7, 1)
		click.setOnClick(() => {
			if (!begin) {
				begin = true
				timer = new Timer
				time = seconds.getValue
				root.remove(seconds)
				timer.schedule(new TimerTask() {
					override def run(): Unit = {
						MinecraftClient.getInstance().execute(() => {
							cps = clicks / time
							MinecraftClient.getInstance.setScreen(new YourCPSIsScreen(new CpsOutputScreen(cps)))
							timer.cancel()
							timer = null
							root.add(seconds, 1, 5, 7, 1)
							begin = false
							time = 0
							clicks = 0
						})
					}
				}, time * 1000)
			}
			clicks += 1
		})
		root.validate(this)
	}

	override def addPainters(): Unit = {
		this.getRootPanel.setBackgroundPainter(BackgroundPainter.VANILLA)
	}
}
