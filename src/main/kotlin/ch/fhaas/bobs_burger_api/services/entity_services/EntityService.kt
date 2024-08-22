package ch.fhaas.bobs_burger_api.services.entity_services

import ch.fhaas.bobs_burger_api.services.RequestClient
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

    protected fun get(parameter: String, amount: Int = 0): JSONArray {
        val url: String = buildUrl(amount, arrayOf(parameter))
        return request(url).array
    }

    private fun request(url: String): JsonNode = requestClient.request(url)

    private fun buildUrl(amount: Int = 0, params: Array<String>): String =
        BASE_URL +
                urlPath +
                parametersToString(params) +
                getLimit(amount)

    private fun parametersToString(params: Array<String>): String {
        return params.fold(StringBuilder()) { string, param ->
            return string.append(param).toString()
        }.toString()
    }

    private fun getLimit(amount: Int) = if (amount > 0) "?limit=$amount" else ""
}
