package ch.fhaas.real_estate_rest_api

import com.mashape.unirest.http.Unirest
import com.mashape.unirest.request.GetRequest
import org.json.JSONArray
import org.springframework.stereotype.Service

@Service
class RequestClient {

    fun request(url: String, headers: Map<String, String>): JSONArray {
        val request: GetRequest = Unirest.get(url)
        setRequestHeaders(request, headers)
        val response = request.asString()
        return JSONArray(response.body)
    }

    private fun setRequestHeaders(request: GetRequest, headers: Map<String, String>) {
        if (headers.isNotEmpty()) request.headers(headers)
    }

}