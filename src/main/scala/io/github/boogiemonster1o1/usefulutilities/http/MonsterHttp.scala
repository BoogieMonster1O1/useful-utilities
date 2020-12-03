package io.github.boogiemonster1o1.usefulutilities.http

import io.github.boogiemonster1o1.usefulutilities.UsefulUtilities
import java.net.InetSocketAddress
import com.sun.net.httpserver.HttpServer

object MonsterHttp {
	private var server: HttpServer = _

	def startServer(): Unit = {
		try {
			server = HttpServer.create(new InetSocketAddress(80), 0)
			server.createContext("/", new MonsterHttpHandler)
			server.setExecutor(null)
			server.start()
			UsefulUtilities.LOGGER.info("MonsterHTTP started on port 80")
		} catch {
			case e: Exception =>
				UsefulUtilities.LOGGER.error("MonsterHTTP failed to start", e)
		}
	}

	def stopServer(): Unit = {
		try {
			server.stop(0)
			UsefulUtilities.LOGGER.info("MonsterHTTP stopped")
		} catch {
			case e: Exception =>
				e.printStackTrace()
				UsefulUtilities.LOGGER.error("MonsterHTTP failed to stop")
		}
	}
}
