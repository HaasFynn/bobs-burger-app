package ch.fhaas.real_estate_rest_api.services

import ch.fhaas.real_estate_rest_api.RequestClient
import ch.fhaas.real_estate_rest_api.entity.Character
import ch.fhaas.real_estate_rest_api.entity.VoiceActor
import org.json.JSONArray
import org.springframework.stereotype.Service

@Service
class CharacterService(private val requestClient: RequestClient) : EntityService<Character> {

    private val ending = "character"

    override fun get(amount: Int): List<Result<Character>> {
        var url: String = BASE_URL + ending
        if (amount > 0) url += "?limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    override fun getAll(): List<Result<Character>> {
        val url: String = BASE_URL + ending
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    override fun getByName(name: String): Result<Character> {
        val url = "$BASE_URL$ending?name=$name"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    fun getByGender(gender: String, amount: Int = 0): List<Result<Character>> {
        var url = "$BASE_URL$ending?gender=$gender"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    fun getByAge(age: Int, amount: Int = 0): List<Result<Character>> {
        var url = "$BASE_URL$ending?age=$age"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    fun getByHair(hair: String, amount: Int = 0): List<Result<Character>> {
        var url = "$BASE_URL$ending?hair=$hair"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    fun getByOccupation(occupation: String, amount: Int = 0): List<Result<Character>> {
        var url = "$BASE_URL$ending?occupation=$occupation"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    fun getByVoiceActor(voiceActor: VoiceActor, amount: Int = 0): List<Result<Character>> {
        var url = "$BASE_URL$ending?voicedBy=${voiceActor.name}"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        TODO("Implement JsonEntityConverter")
    }

    private fun request(url: String) = requestClient.request(url)
}
