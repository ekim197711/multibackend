package com.codeinvestigator.space

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFacadeImpl : DAOFacade {

    private fun resultRowToSpaceShip(row: ResultRow) = SpaceShip(
        id = row[SpaceShips.id],
        model = row[SpaceShips.model],
        destination = row[SpaceShips.destination],
        fuelLeft = row[SpaceShips.fuelLeft],
        captain = row[SpaceShips.captain]
    )

    override suspend fun allSpaceShips(): List<SpaceShip> =
        DatabaseFactory.dbQuery {
            SpaceShips.selectAll().map(::resultRowToSpaceShip)
        }

    override suspend fun spaceShip(id: Int): SpaceShip? =
        DatabaseFactory.dbQuery {
            SpaceShips.select { SpaceShips.id eq id }
                .map(::resultRowToSpaceShip)
                .singleOrNull()
        }

    override suspend fun addNewSpaceShip(
        ship: SpaceShip
    ): SpaceShip? = addNewSpaceShip(ship.model, ship.fuelLeft, ship.destination, ship.captain)

    override suspend fun addNewSpaceShip(
        model: String,
        fuelLeft: Float,
        destination: String,
        captain: String
    ): SpaceShip? = DatabaseFactory.dbQuery {
        val insertStatement = SpaceShips.insert {
            it[SpaceShips.captain] = captain
            it[SpaceShips.model] = model
            it[SpaceShips.fuelLeft] = fuelLeft
            it[SpaceShips.destination] = destination
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToSpaceShip)
    }


    override suspend fun editSpaceShip(
        id: Int,
        model: String,
        fuelLeft: Float,
        destination: String,
        captain: String
    ): Boolean = DatabaseFactory.dbQuery {
        SpaceShips.update({ SpaceShips.id eq id }) {
            it[SpaceShips.model] = model
            it[SpaceShips.captain] = captain
            it[SpaceShips.fuelLeft] = fuelLeft
            it[SpaceShips.destination] = destination
        } > 0
    }

    override suspend fun deleteSpaceShip(id: Int): Boolean = DatabaseFactory.dbQuery {
        SpaceShips.deleteWhere { SpaceShips.id eq id } > 0
    }

}

val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if (allSpaceShips().isEmpty()) {
            addNewSpaceShip(
                "Sparrow",
                88.233f,
                "Mars", "Mike"
            )
            addNewSpaceShip(
                "Falcon",
                48.1f,
                "Venus", "Katja"
            )
            addNewSpaceShip(
                "Swan",
                11f,
                "Moon", "Elon"
            )
        }
    }
}