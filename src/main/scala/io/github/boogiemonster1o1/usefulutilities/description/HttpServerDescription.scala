package io.github.boogiemonster1o1.usefulutilities.description

import io.github.cottonmc.cotton.gui.client.{CottonClientScreen, LightweightGuiDescription}
import io.github.cottonmc.cotton.gui.widget.{WButton, WGridPanel}
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen
import net.minecraft.text.TranslatableText
import net.minecraft.util.Util


class HttpServerDescription @throws[RuntimeException]() extends LightweightGuiDescription {
	{
		val root = new WGridPanel
		root.setSize(256, 128)
		setRootPanel(root)
		val visit = new WButton(new TranslatableText("gui.utilities.monsterhttp.visit"))
		root.add(visit, 1, 3, 12, 1)
		visit.setOnClick(() => {
			MinecraftClient.getInstance.setScreen(new ConfirmChatLinkScreen((bl: Boolean) => {
				if (bl) Util.getOperatingSystem.open("http://localhost:80")
				MinecraftClient.getInstance.setScreen(new CottonClientScreen(new TranslatableText("gui.utilities.monsterhttp"), this))
			}, "http://localhost:80", true))
		})
		val open = new WButton(new TranslatableText("gui.utilities.monsterhttp.open"))
		root.add(open, 1, 5, 12, 1)
		open.setOnClick(() => {
			Util.getOperatingSystem.open(MinecraftClient.getInstance.runDirectory.toPath.resolve("web.txt").toUri)
		})
		root.validate(this)
	}
}
