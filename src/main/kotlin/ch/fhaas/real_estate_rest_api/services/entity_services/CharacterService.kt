package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.entity.Character
import ch.fhaas.real_estate_rest_api.entity.VoiceActor
import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestClient
import ch.fhaas.real_estate_rest_api.services.ResultHandler
import org.json.JSONArray
import org.springframework.stereotype.Service

@Service
class CharacterService(
    jsonReader: JsonReader, resultHandler: ResultHandler, requestClient: RequestClient
) : EntityService<Character>(jsonReader, resultHandler, requestClient) {
    companion object {
        private const val ENDING = "character"
    }

    override val getAction = jsonReader::getCharacter


    override fun get(amount: Int): List<Character> {
        var url: String = BASE_URL + ENDING
        if (amount > 0) url += "?limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    override fun getAll(): List<Character> {
        val url: String = BASE_URL + ENDING
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    override fun getByName(name: String): Character? {
        val url = "${BASE_URL}${ENDING}?name=$name"
        val json: JSONArray = request(url)
        val result: Result<Character> = jsonReader.getCharacter(json.getJSONObject(0))
        return resultHandler.getEntityOfResult(result)
    }

    fun getByGender(gender: String, amount: Int = 0): List<Character> {
        var url = "${BASE_URL}${ENDING}?gender=$gender"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getByAge(age: Int, amount: Int = 0): List<Character> {
        var url = "${BASE_URL}${ENDING}?age=$age"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getByHair(hair: String, amount: Int = 0): List<Character> {
        var url = "${BASE_URL}${ENDING}?hair=$hair"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getByOccupation(occupation: String, amount: Int = 0): List<Character> {
        var url = "${BASE_URL}${ENDING}?occupation=$occupation"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getByVoiceActor(voiceActor: VoiceActor, amount: Int = 0): List<Character> {
        var url = "${BASE_URL}${ENDING}?voicedBy=${voiceActor.name}"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

}
