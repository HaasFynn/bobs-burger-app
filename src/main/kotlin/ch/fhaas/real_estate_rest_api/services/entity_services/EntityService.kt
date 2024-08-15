package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.entity.Entity
import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestClient
import ch.fhaas.real_estate_rest_api.services.ResultHandler
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONObject
import kotlin.reflect.KFunction1

abstract class EntityService<T : Entity>(
    protected val jsonReader: JsonReader,
    protected val resultHandler: ResultHandler,
    private val requestClient: RequestClient,
    private val urlPath: String,
    protected val getAction: KFunction1<JSONObject, T?>
) {
    companion object {
        @JvmStatic
        private val BASE_URL: String = "https://bobsburgers-api.herokuapp.com/"
    }

    fun get(amount: Int): List<T> =
        getAmount(amount)

    fun getAll(): List<T> =
        getAmount()


    protected fun get(url: String): T? {
        val json: JSONArray = request(url)
        return getAction(json.getJSONObject(0))
    }

    protected fun getAmount(amount: Int = 0, urlExtra: String = ""): List<T> {
        val url: String = buildUrl(amount, urlExtra)
        val json: JSONArray = request(url)
        return getListOfEntities(json)
    }

    private fun getListOfEntities(json: JSONArray): List<T> =
        (0 until json.length()).fold(emptyList()) { list, index ->
            val entity: T? = getAction(json.getJSONObject(index))
            entity?.let { list + it } ?: list
        }

    private fun request(url: String) = requestClient.request(url)

    private fun buildUrl(amount: Int = 0, extras: String): String {
        return BASE_URL + urlPath + extras + if (amount > 0) "?amount=$amount" else ""
    }

}
