package ch.fhaas.real_estate_rest_api.services.entity_services

const val BASE_URL: String = "https://bobsburgers-api.herokuapp.com/"

sealed interface EntityService<Entity> {
    fun get(amount: Int): List<Entity>
    fun getAll(): List<Entity>
    fun getByName(name: String): Entity?
}
