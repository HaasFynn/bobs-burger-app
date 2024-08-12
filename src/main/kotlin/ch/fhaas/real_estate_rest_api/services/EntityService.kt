package ch.fhaas.real_estate_rest_api.services

const val BASE_URL: String = "https://bobsburgers-api.herokuapp.com/"

sealed interface EntityService<Entity> {
    fun get(amount: Int): Result<List<Entity>>
    fun getAll(): Result<List<Entity>>
    fun getByName(name: String): Result<Entity>
}
