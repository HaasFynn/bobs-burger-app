package ch.fhaas.real_estate_rest_api.services

import kong.unirest.core.Unirest
import kong.unirest.core.UnirestException
import kong.unirest.core.json.JSONArray
import org.springframework.stereotype.Service

@Service
class
RequestClient {
    fun request(url: String, headers: Map<String, String> = emptyMap()): JSONArray {
        return try {
            val request = Unirest.get(url).headers(headers)
            val response = request.asJson()
            response.body.array
        } catch (e: UnirestException) {
            e.printStackTrace()
            JSONArray()
        }
    }
}