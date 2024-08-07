package ch.fhaas.real_estate_rest_api.services

import ch.fhaas.real_estate_rest_api.RequestClient
import ch.fhaas.real_estate_rest_api.entity.Character
import ch.fhaas.real_estate_rest_api.entity_properties.VoiceActor
import org.json.JSONArray
import org.springframework.stereotype.Service

@Service
class CharacterService(private val requestClient: RequestClient) : EntityService<Character>() {

    private val ending = "character"

    override fun get(amount: Int): List<Character> {
        var url: String = base + ending
        if (amount > 0) url += "?limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    override fun getAll(): List<Character>? {
        val url: String = base + ending
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    override fun getByName(name: String): Character? {
        val url = "$base$ending?name=$name"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    fun getByGender(gender: String, amount: Int = 0): List<Character>? {
        var url = "$base$ending?gender=$gender"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    fun getByAge(age: Int, amount: Int = 0): List<Character>? {
        var url = "$base$ending?age=$age"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    fun getByHair(hair: String, amount: Int = 0): List<Character>? {
        var url = "$base$ending?hair=$hair"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    fun getByOccupation(occupation: String, amount: Int = 0): List<Character>? {
        var url = "$base$ending?occupation=$occupation"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    fun getByVoiceActor(voiceActor: VoiceActor, amount: Int = 0): List<Character>? {
        var url = "$base$ending?voicedBy=${voiceActor.name}"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    private fun request(url: String) = requestClient.request(url, emptyMap())
}
