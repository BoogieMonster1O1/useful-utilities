package io.github.boogiemonster1o1.usefulutilities.widget

import io.github.boogiemonster1o1.usefulutilities.description.PianoDescription
import io.github.cottonmc.cotton.gui.client.ScreenDrawing
import io.github.cottonmc.cotton.gui.widget.WWidget
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class PianoKeySprite(val pitch: Float, val keyColor: PianoDescription.KeyColor.KeyColor, val text: Text, val keyLabel: Text) extends WWidget {
	private var texture: Identifier = _
	private var label: Text = _

	if (keyColor eq PianoDescription.KeyColor.BLACK) {
		this.setSize(25, 50)
		this.texture = new Identifier("usefulutilities", "textures/black.png")
		this.label = text
	}
	else if (keyColor eq PianoDescription.KeyColor.WHITE) {
		this.setSize(30, 80)
		this.texture = new Identifier("usefulutilities", "textures/white.png")
		this.label = text
	}

	override def onClick(x: Int, y: Int, button: Int): Unit = {
		if (MinecraftClient.getInstance.player == null) return
		MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, this.pitch)
	}

	override def paint(matrices: MatrixStack, x: Int, y: Int, mouseX: Int, mouseY: Int): Unit = {
		super.paint(matrices, x, y, mouseX, mouseY)
		ScreenDrawing.texturedRect(matrices, x, y, this.width, this.height, this.texture, 0xFFFFFFFF)
		if (this.keyColor eq PianoDescription.KeyColor.WHITE) {
			ScreenDrawing.drawString(matrices, this.label.asString, HorizontalAlignment.CENTER, x, y + 55, this.width, 0x000000)
			ScreenDrawing.drawString(matrices, this.keyLabel.asString, HorizontalAlignment.CENTER, x, y + 70, this.width, 0xFF0000)
		}
		else if (this.keyColor eq PianoDescription.KeyColor.BLACK) {
			ScreenDrawing.drawString(matrices, this.label.asString, HorizontalAlignment.CENTER, x, y + 20, this.width, 0xFFFFFF)
			ScreenDrawing.drawString(matrices, this.keyLabel.asString, HorizontalAlignment.CENTER, x, y + 35, this.width, 0xFF0000)
		}
	}
}
