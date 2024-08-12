package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestClient
import ch.fhaas.real_estate_rest_api.services.ResultHandler
import ch.fhaas.real_estate_rest_api.entity.Character
import ch.fhaas.real_estate_rest_api.entity.VoiceActor
import org.json.JSONArray
import org.springframework.stereotype.Service

@Service
class CharacterService(
    private val requestClient: RequestClient,
    private val jsonReader: JsonReader,
    private val resultHandler: ResultHandler
) : EntityService<Character> {

    private val ending = "character"

    override fun get(amount: Int): List<Character> {
        var url: String = BASE_URL + ending
        if (amount > 0) url += "?limit=$amount"
        val json: JSONArray = request(url)
        return getListOfCharacters(json)
    }

    override fun getAll(): List<Character> {
        val url: String = BASE_URL + ending
        val json: JSONArray = request(url)
        return getListOfCharacters(json)
    }

    override fun getByName(name: String): Character? {
        val url = "$BASE_URL$ending?name=$name"
        val json: JSONArray = request(url)
        val result: Result<Character> = jsonReader.getCharacter(json.getJSONObject(0))
        return resultHandler.getEntityOfResult(result)
    }

    fun getByGender(gender: String, amount: Int = 0): List<Character> {
        var url = "$BASE_URL$ending?gender=$gender"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfCharacters(json)
    }

    fun getByAge(age: Int, amount: Int = 0): List<Character> {
        var url = "$BASE_URL$ending?age=$age"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfCharacters(json)
    }

    fun getByHair(hair: String, amount: Int = 0): List<Character> {
        var url = "$BASE_URL$ending?hair=$hair"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfCharacters(json)
    }

    fun getByOccupation(occupation: String, amount: Int = 0): List<Character> {
        var url = "$BASE_URL$ending?occupation=$occupation"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfCharacters(json)
    }

    fun getByVoiceActor(voiceActor: VoiceActor, amount: Int = 0): List<Character> {
        var url = "$BASE_URL$ending?voicedBy=${voiceActor.name}"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfCharacters(json)
    }

    private fun getListOfCharacters(json: JSONArray): List<Character> =
        (0 until json.length()).fold(emptyList()) { list, index ->
            val result: Result<Character> = jsonReader.getCharacter(json.getJSONObject(index))
            val entity: Character? = resultHandler.getEntityOfResult(result)
            entity?.let { list + it } ?: list
        }

    private fun request(url: String) = requestClient.request(url)
}
