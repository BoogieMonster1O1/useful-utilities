package io.github.boogiemonster1o1.usefulutilities.description

import io.github.cottonmc.cotton.gui.client.{BackgroundPainter, LightweightGuiDescription}
import io.github.cottonmc.cotton.gui.widget.{WGridPanel, WLabel}
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.resource.language.I18n
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.TranslatableText
import net.minecraft.util.registry.Registry
import net.minecraft.world.{Difficulty, World}
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk

class WorldInfoDescription() extends LightweightGuiDescription {
	{
		val root = new WGridPanel
		root.setSize(356, 204)
		setRootPanel(root)
		val label = new WLabel(new TranslatableText("gui.utilities.world"))
		root.add(label, 5, 1, 10, 1)
		label.setHorizontalAlignment(HorizontalAlignment.CENTER)
		val player: PlayerEntity = MinecraftClient.getInstance.player
		val world: World = player.world
		val playerbiome: Biome = world.getBiome(player.getBlockPos)
		val chunk: Chunk = world.getChunk(player.getBlockPos)
		val difficulty: Difficulty = world.getDifficulty
		val time: Long = world.getTimeOfDay
		val raining: Boolean = world.isRaining
		val thundering: Boolean = world.isThundering
		var weather: String = I18n.translate("gui.utilities.world.clear")
		if (raining) weather = I18n.translate("gui.utilities.world.raining")
		if (thundering) weather = I18n.translate("gui.utilities.world.thundering")
		var x: Double = player.getX
		x = Math.floor(x * 10000) / 10000.0
		var y: Double = player.getY
		y = Math.floor(y * 10000) / 10000.0
		var z: Double = player.getZ
		z = Math.floor(z * 10000) / 10000.0
		val biome: String = world.getRegistryManager.get(Registry.BIOME_KEY).getId(world.getBiome(player.getBlockPos)).toString
		val pchunk: String = chunk.getPos.toString
		val diff: String = difficulty.toString
		val wtime: String = time.toString
		val wthr: String = weather
		val uuid: String = player.getUuidAsString
		val pos: String = "X:" + x + " Y:" + y + " Z:" + z
		val biomeLabel = new WLabel(I18n.translate("gui.utilities.world.biome") + biome)
		val pchunkLabel = new WLabel(I18n.translate("gui.utilities.world.chunk") + pchunk)
		val diffLabel = new WLabel(I18n.translate("gui.utilities.world.difficulty") + diff)
		val wtimeLabel = new WLabel(I18n.translate("gui.utilities.world.time") + wtime)
		val wthrLabel = new WLabel(I18n.translate("gui.utilities.world.weather") + wthr)
		val uuidLabel = new WLabel(I18n.translate("gui.utilities.world.uuid") + uuid)
		val posLabel = new WLabel(I18n.translate("gui.utilities.world.pos") + pos)
		root.add(posLabel, 1, 3, 8, 1)
		root.add(uuidLabel, 1, 4, 8, 1)
		root.add(wthrLabel, 1, 5, 8, 1)
		root.add(diffLabel, 1, 6, 8, 1)
		root.add(pchunkLabel, 1, 7, 8, 1)
		root.add(biomeLabel, 1, 8, 8, 1)
		root.add(wtimeLabel, 1, 9, 8, 1)
		root.validate(this)
	}

	override def addPainters(): Unit = {
		getRootPanel.setBackgroundPainter(BackgroundPainter.VANILLA)
	}
}
