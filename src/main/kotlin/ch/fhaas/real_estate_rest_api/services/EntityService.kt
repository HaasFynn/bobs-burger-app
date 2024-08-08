package ch.fhaas.real_estate_rest_api.services

sealed interface EntityService<Entity> {
    val base: String
        get() = "https://bobsburgers-api.herokuapp.com/"

    fun get(amount: Int): List<Entity>?
    fun getAll(): List<Entity>?
    fun getByName(name: String): Entity?
}
