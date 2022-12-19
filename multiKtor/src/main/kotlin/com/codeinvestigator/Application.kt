package com.codeinvestigator

import com.codeinvestigator.plugins.configureRouting
import com.codeinvestigator.plugins.configureSerialization
import com.codeinvestigator.space.DatabaseFactory
import io.ktor.server.application.*

//fun main() {
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
//        .start(wait = true)
//}
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init()
    configureSerialization()
    configureRouting()
}
