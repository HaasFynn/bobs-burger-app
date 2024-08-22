package ch.fhaas.bobs_burger_api.services

import org.springframework.stereotype.Service

@Service
class RequestBuilder {

    companion object {
        @JvmStatic
        private val BASE_URL: String = "https://bobsburgers-api.herokuapp.com/"
    }

    fun buildUrl(directory: String, params: Map<String, Any> = emptyMap()): String {
        var url = "$BASE_URL$directory"
        if (params.isNotEmpty()) url += "?"
        params.forEach { param -> url += "${param.key}=${param.value}&" }
        return url
    }
}