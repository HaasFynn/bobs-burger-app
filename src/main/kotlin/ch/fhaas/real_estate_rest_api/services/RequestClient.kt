package ch.fhaas.real_estate_rest_api.services

import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.exceptions.UnirestException
import org.json.JSONArray
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