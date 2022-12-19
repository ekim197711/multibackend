package com.codeinvestigator.plugins

import com.codeinvestigator.space.SpaceShip
import com.codeinvestigator.space.dao
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello from ktor with kotlin world!")
        }
        get("/spaceship") {
            call.respond(dao.allSpaceShips())
        }
        post("/spaceship") {
            val newShip = call.receive<SpaceShip>()
            val ship = dao.addNewSpaceShip(newShip)
            call.respond(ship as SpaceShip)
        }
    }
}

