package io.github.boogiemonster1o1.usefulutilities.description

import io.github.cottonmc.cotton.gui.client.{BackgroundPainter, LightweightGuiDescription}
import io.github.cottonmc.cotton.gui.widget.{WButton, WGridPanel, WLabel}
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment
import net.minecraft.client.MinecraftClient
import net.minecraft.text.TranslatableText

class GameCrashersDescription() extends LightweightGuiDescription {
	{
		val root = new WGridPanel
		setRootPanel(root)
		root.setSize(160, 220)
		val label = new WLabel(new TranslatableText("gui.utilities.crash"), 0x000000)
		label.setHorizontalAlignment(HorizontalAlignment.CENTER)
		root.add(label, 1, 1, 7, 1)
		val normal = new WButton(new TranslatableText("gui.utilities.crash.normal"))
		root.add(normal, 1, 3, 7, 1)
		normal.setOnClick(() => MinecraftClient.getInstance.scheduleStop())
		val stackoverflow = new WButton(new TranslatableText("gui.utilities.crash.stackoverflow"))
		root.add(stackoverflow, 1, 5, 7, 1)
		stackoverflow.setOnClick(() => {
			throw new StackOverflowError("Never Gonna Give you up!")
		})
		val outofmemory = new WButton(new TranslatableText("gui.utilities.crash.outofmemory"))
		root.add(outofmemory, 1, 7, 7, 1)
		outofmemory.setOnClick(() => {
			throw new OutOfMemoryError("Never Gonna Let you down!")
		})
		val security = new WButton(new TranslatableText("gui.utilities.crash.security"))
		root.add(security, 1, 9, 7, 1)
		security.setOnClick(() => {
			throw new SecurityException("Never Gonna turn around and desert you!")
		})
		val nullpointer = new WButton(new TranslatableText("gui.utilities.crash.nullpointer"))
		root.add(nullpointer, 1, 11, 7, 1)
		nullpointer.setOnClick(() => {
			throw new NullPointerException("Never Gonna make you cry!")
		})
		val internal = new WButton(new TranslatableText("gui.utilities.crash.internal"))
		root.add(internal, 1, 13, 7, 1)
		internal.setOnClick(() => {
			throw new InternalError("Never Gonna say goodbye")
		})
	}

	override def addPainters(): Unit = {
		getRootPanel.setBackgroundPainter(BackgroundPainter.VANILLA)
	}
}
