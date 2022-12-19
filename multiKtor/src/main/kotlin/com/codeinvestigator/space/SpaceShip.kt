package com.codeinvestigator.space

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class SpaceShip(
    val id: Int? = null, val model: String, @Contextual val fuelLeft: Float,
    val captain: String, val destination: String
)

object SpaceShips : Table() {
    val id = integer("id").autoIncrement()
    val model = varchar("model", 128)
    val fuelLeft = float("fuel_left")
    val captain = varchar("captain", (128))
    val destination = varchar("destination", 256)

    override val primaryKey = PrimaryKey(id)
}