package io.github.boogiemonster1o1.usefulutilities.description

import io.github.boogiemonster1o1.usefulutilities.widget.PianoKeySprite
import io.github.cottonmc.cotton.gui.client.BackgroundPainter
import io.github.cottonmc.cotton.gui.client.CottonClientScreen
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription
import io.github.cottonmc.cotton.gui.widget.WPlainPanel
import org.lwjgl.glfw.GLFW
import net.minecraft.client.MinecraftClient
import net.minecraft.sound.SoundEvents
import net.minecraft.text.LiteralText
import net.minecraft.text.TranslatableText
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.gui.screen.Screen

@Environment(EnvType.CLIENT) object PianoDescription {
	class KeyboardMainScreen() extends CottonClientScreen(new TranslatableText("gui.utilities.keyboard"), new PianoDescription) {
		override def onClose(): Unit = {
			MinecraftClient.getInstance.setScreen(new CottonClientScreen(new UtilitiesListDescription))
		}

		override def keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean = {
			if (keyCode == GLFW.GLFW_KEY_ESCAPE && this.shouldCloseOnEsc) {
				this.onClose()
				true
			}
			else if (keyCode == GLFW.GLFW_KEY_TAB) {
				val bl = !Screen.hasShiftDown
				if (!this.changeFocus(bl)) this.changeFocus(bl)
				true
			}
			else {
				if (keyCode == GLFW.GLFW_KEY_A) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.7f)
				else if (keyCode == GLFW.GLFW_KEY_W) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.75f)
				else if (keyCode == GLFW.GLFW_KEY_S) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.8f)
				else if (keyCode == GLFW.GLFW_KEY_E) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.85f)
				else if (keyCode == GLFW.GLFW_KEY_D) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.9f)
				else if (keyCode == GLFW.GLFW_KEY_F) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 0.95f)
				else if (keyCode == GLFW.GLFW_KEY_T) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.0f)
				else if (keyCode == GLFW.GLFW_KEY_G) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.06f)
				else if (keyCode == GLFW.GLFW_KEY_Y) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.12f)
				else if (keyCode == GLFW.GLFW_KEY_H) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.18f)
				else if (keyCode == GLFW.GLFW_KEY_U) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.24f)
				else if (keyCode == GLFW.GLFW_KEY_J) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.32f)
				else if (keyCode == GLFW.GLFW_KEY_K) MinecraftClient.getInstance.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.4f)
				else return super.keyPressed(keyCode, scanCode, modifiers)
				true
			}
		}
	}

	object KeyColor extends Enumeration {
		type KeyColor = Value
		val WHITE, BLACK = Value
	}

}

@Environment(EnvType.CLIENT) class PianoDescription private() extends LightweightGuiDescription {
	{
		val root = new WPlainPanel
		root.setSize(306, 204)
		setRootPanel(root)
		val xC = 10
		val yC = 30
		val MIDDLE_C = new PianoKeySprite(0.7f, PianoDescription.KeyColor.WHITE, new LiteralText("C"), new LiteralText("A"))
		val C_SHARP = new PianoKeySprite(0.75f, PianoDescription.KeyColor.BLACK, new LiteralText("C#"), new LiteralText("W"))
		val D = new PianoKeySprite(0.8f, PianoDescription.KeyColor.WHITE, new LiteralText("D"), new LiteralText("S"))
		val D_SHARP = new PianoKeySprite(0.85f, PianoDescription.KeyColor.BLACK, new LiteralText("D#"), new LiteralText("E"))
		val E = new PianoKeySprite(0.9f, PianoDescription.KeyColor.WHITE, new LiteralText("E"), new LiteralText("D"))
		val F = new PianoKeySprite(0.95f, PianoDescription.KeyColor.WHITE, new LiteralText("F"), new LiteralText("F"))
		val F_SHARP = new PianoKeySprite(1.0f, PianoDescription.KeyColor.BLACK, new LiteralText("F#"), new LiteralText("T"))
		val G = new PianoKeySprite(1.06f, PianoDescription.KeyColor.WHITE, new LiteralText("G"), new LiteralText("G"))
		val G_SHARP = new PianoKeySprite(1.12f, PianoDescription.KeyColor.BLACK, new LiteralText("G#"), new LiteralText("Y"))
		val A = new PianoKeySprite(1.18f, PianoDescription.KeyColor.WHITE, new LiteralText("A"), new LiteralText("H"))
		val A_SHARP = new PianoKeySprite(1.24f, PianoDescription.KeyColor.BLACK, new LiteralText("A#"), new LiteralText("U"))
		val B = new PianoKeySprite(1.32f, PianoDescription.KeyColor.WHITE, new LiteralText("B"), new LiteralText("J"))
		val UPPER_C = new PianoKeySprite(1.4f, PianoDescription.KeyColor.WHITE, new LiteralText("C"), new LiteralText("K"))
		root.add(MIDDLE_C, xC + 10, yC + 20, 30, 80)
		root.add(D, xC + 40 + 3, yC + 20, 30, 80)
		root.add(E, xC + 70 + 6, yC + 20, 30, 80)
		root.add(F, xC + 100 + 9, yC + 20, 30, 80)
		root.add(G, xC + 130 + 12, yC + 20, 30, 80)
		root.add(A, xC + 160 + 15, yC + 20, 30, 80)
		root.add(B, xC + 190 + 18, yC + 20, 30, 80)
		root.add(UPPER_C, xC + 220 + 21, yC + 20, 30, 80)
		root.add(C_SHARP, xC + 29, yC + 20, 25, 50)
		root.add(D_SHARP, xC + 62, yC + 20, 25, 50)
		root.add(F_SHARP, xC + 128, yC + 20, 25, 50)
		root.add(G_SHARP, xC + 161, yC + 20, 25, 50)
		root.add(A_SHARP, xC + 194, yC + 20, 25, 50)
		root.validate(this)
	}

	override def addPainters(): Unit = {
		getRootPanel.setBackgroundPainter(BackgroundPainter.VANILLA)
	}
}
