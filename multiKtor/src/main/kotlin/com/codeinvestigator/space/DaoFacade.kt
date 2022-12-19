package com.codeinvestigator.space


interface DAOFacade {
    suspend fun allSpaceShips(): List<SpaceShip>
    suspend fun spaceShip(id: Int): SpaceShip?
    suspend fun addNewSpaceShip(model: String, fuelLeft: Float, destination: String, captain: String): SpaceShip?
    suspend fun addNewSpaceShip(ship: SpaceShip): SpaceShip?
    suspend fun editSpaceShip(
        id: Int,
        model: String,
        fuelLeft: Float,
        destination: String,
        captain: String
    ): Boolean

    suspend fun deleteSpaceShip(id: Int): Boolean
}