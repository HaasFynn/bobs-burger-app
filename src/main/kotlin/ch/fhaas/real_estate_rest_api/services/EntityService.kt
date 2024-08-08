package ch.fhaas.real_estate_rest_api.services

sealed interface EntityService<T> {
    val base: String
        get() = "https://bobsburgers-api.herokuapp.com/"

    fun get(amount: Int): List<T>?
    fun getAll(): List<T>?
    fun getByName(name: String): T?
}
