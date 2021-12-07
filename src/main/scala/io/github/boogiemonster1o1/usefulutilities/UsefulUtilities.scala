package io.github.boogiemonster1o1.usefulutilities

import io.github.boogiemonster1o1.usefulutilities.api.{UsefulUtilitiesApi, UtilityManager}
import io.github.boogiemonster1o1.usefulutilities.description.UtilitiesListDescription
import io.github.boogiemonster1o1.usefulutilities.http.MonsterHttp
import io.github.cottonmc.cotton.gui.client.CottonClientScreen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.loader.impl.entrypoint.EntrypointUtils
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import org.apache.logging.log4j.{LogManager, Logger, MarkerManager}
import org.lwjgl.glfw.GLFW

object UsefulUtilities extends ClientModInitializer {
	val LOGGER: Logger = LogManager.getLogger("UsefulUtilities")

	override def onInitializeClient(): Unit = {
		LOGGER.info(MarkerManager.getMarker("Useful Utilities"), "Starting Useful Utilities...")
		val key = KeyBindingHelper.registerKeyBinding(new KeyBinding("gui.utilities", GLFW.GLFW_KEY_U, "key.categories.misc"))
		ClientTickEvents.END_CLIENT_TICK.register((client: MinecraftClient) => {
			if (key.wasPressed()) {
				client.setScreen(new CottonClientScreen(new UtilitiesListDescription))
			}
		})
		MonsterHttp.startServer()
		val manager: UtilityManager = UtilityManager.getInstance
		EntrypointUtils.invoke("usefulutilities", classOf[UsefulUtilitiesApi], (api: UsefulUtilitiesApi) => api.initializeUtilities(manager))
	}
}
