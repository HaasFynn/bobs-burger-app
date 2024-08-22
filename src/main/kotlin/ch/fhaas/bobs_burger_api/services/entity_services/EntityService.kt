package ch.fhaas.bobs_burger_api.services.entity_services

import ch.fhaas.bobs_burger_api.services.RequestBuilder
import ch.fhaas.bobs_burger_api.services.RequestClient
import kong.unirest.core.JsonNode
import kong.unirest.core.json.JSONArray

abstract class EntityService(
    private val requestClient: RequestClient,
    private val requestBuilder: RequestBuilder,
    private val directory: String
) {

    protected fun get(parameter: Pair<String, Any>, amount: Int = 0): JSONArray {
        val url: String = buildUrl(amount, parameter)
        return request(url).array
    }

    private fun request(url: String): JsonNode = requestClient.request(url)

    private fun buildUrl(amount: Int = 0, params:  Pair<String, Any>): String = requestBuilder.buildUrl(directory, mapOf(params)) + getLimit(amount)

    private fun getLimit(amount: Int) = if (amount > 0) "?limit=$amount" else ""
}