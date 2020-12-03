package io.github.boogiemonster1o1.usefulutilities.http

import java.io.{File, IOException}
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.util

import com.sun.net.httpserver.{HttpExchange, HttpHandler}
import net.minecraft.client.MinecraftClient

class MonsterHttpHandler extends HttpHandler {
	override def handle(exchange: HttpExchange): Unit = {
		var response = "<html><h1>It Works!</h1></html>"
		try {
			val webFile = new File(MinecraftClient.getInstance.runDirectory + File.separator + "web.txt")
			if (!webFile.exists) {
				if (!webFile.createNewFile) throw new IOException("Unable to create file")
				Files.write(webFile.toPath, response.getBytes(StandardCharsets.UTF_8))
			}
			response = util.Arrays.toString(Files.readAllLines(webFile.toPath).toArray).replace(",", "").replace("[", "").replace("]", "")
			exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length)
			val writer = exchange.getResponseBody
			writer.write(response.getBytes)
			writer.flush()
			writer.close()
		} catch {
			case e: Exception =>
				e.printStackTrace()
		}
	}
}
