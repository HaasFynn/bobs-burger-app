package ch.fhaas.real_estate_rest_api.services

import org.springframework.stereotype.Service

@Service
class RequestBuilder {

    companion object {
        @JvmStatic
        private val BASE_URL: String = "https://bobsburgers-api.herokuapp.com/"
    }

    fun buildUrl(directory: String, params: HashMap<String, Any?>): String {
        var url = "$BASE_URL$directory?"
        params.forEach { param -> url += param.value?.let { "${param.key}=${param.value}&" } ?: "" }
        return url
    }

}