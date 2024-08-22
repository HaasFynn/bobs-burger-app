package ch.fhaas.bobs_burger_api.services

import kong.unirest.core.JsonNode
import kong.unirest.core.Unirest
import org.springframework.stereotype.Service

@Service
class RequestClient {
    fun request(url: String, headers: Map<String, String> = emptyMap()): JsonNode {
        return try {
            val request = Unirest.get(url).headers(headers)
            val response = request.asJson()
            response.body
        } catch (e: Exception) {
            e.printStackTrace()
            JsonNode("")
        }
    }
}