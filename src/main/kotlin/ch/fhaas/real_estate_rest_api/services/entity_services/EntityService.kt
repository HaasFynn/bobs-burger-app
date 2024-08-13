package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.entity.Entity
import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestClient
import ch.fhaas.real_estate_rest_api.services.ResultHandler
import org.json.JSONArray
import org.json.JSONObject
import kotlin.reflect.KFunction1

abstract class EntityService<T : Entity>(
    protected val jsonReader: JsonReader,
    protected val resultHandler: ResultHandler,
    private val requestClient: RequestClient
) {
    companion object {
        @JvmStatic
        protected val BASE_URL: String = "https://bobsburgers-api.herokuapp.com/"
    }

    protected abstract val getAction: KFunction1<JSONObject, Result<T>>

    abstract fun get(amount: Int): List<T>
    abstract fun getAll(): List<T>

    protected fun <T> getListOfEntity(json: JSONArray, getAction: (json: JSONObject) -> Result<T>): List<T> =
        (0 until json.length()).fold(emptyList()) { list, index ->
            val result: Result<T> = getAction(json.getJSONObject(index))
            val entity: T? = resultHandler.getEntityOfResult(result)
            entity?.let { list + it } ?: list
        }

    protected fun request(url: String) = requestClient.request(url)

}
