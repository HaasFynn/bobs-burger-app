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
    private val requestClient: RequestClient,
    protected val urlPath: String,
    protected val getAction: KFunction1<JSONObject, T?>
) {
    companion object {
        @JvmStatic
        protected val BASE_URL: String = "https://bobsburgers-api.herokuapp.com/"
    }

    fun get(amount: Int): List<T> =
        getAmount(BASE_URL + urlPath, amount)

    fun getAll(): List<T> =
        getAmount(BASE_URL + urlPath, 0)


    protected fun get(url: String): T? {
        val json: JSONArray = request(url)
        return getAction(json.getJSONObject(0))
    }

    protected fun getAmount(url: String, amount: Int): List<T> {
        var varUrl: String = url
        if (amount > 0) varUrl += "&amount=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    private fun <T> getListOfEntity(json: JSONArray, getAction: (json: JSONObject) -> T?): List<T> =
        (0 until json.length()).fold(emptyList()) { list, index ->
            val entity: T? = getAction(json.getJSONObject(index))
            entity?.let { list + it } ?: list
        }

    private fun request(url: String) = requestClient.request(url)

}
