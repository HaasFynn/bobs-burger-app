package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.services.RequestClient
import kong.unirest.core.JsonNode
import kong.unirest.core.json.JSONArray

abstract class EntityService(
    private val requestClient: RequestClient,
    private val urlPath: String
) {
    companion object {
        @JvmStatic
        private val BASE_URL: String = "https://bobsburgers-api.herokuapp.com/"
    }

    protected fun get(parameter: String, amount: Int): JSONArray {
        val url: String = buildUrl(amount, parameter)
        return request(url).array
    }

    private fun request(url: String): JsonNode = requestClient.request(url)

    private fun buildUrl(amount: Int = 0, vararg parameters: String): String =
        BASE_URL +
                urlPath +
                parameters +
                getLimit(amount)

    private fun getLimit(amount: Int) = if (amount > 0) "?limit=$amount" else ""
}
