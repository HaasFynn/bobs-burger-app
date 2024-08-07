package ch.fhaas.real_estate_rest_api.services

sealed class EntityService<T> {
    val base: String
        get() = "https://bobsburgers-api.herokuapp.com/"

    abstract fun get(amount: Int): List<T>?
    abstract fun getAll(): List<T>?
    abstract fun getByName(name: String): T?
}
