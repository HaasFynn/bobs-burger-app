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
) : EntityService<Character>(jsonReader, resultHandler, requestClient, "character", jsonReader::getCharacter) {

    fun getByName(name: String): Character? {
        val url = "${BASE_URL}${urlPath}?name=$name"
        val json: JSONArray = request(url)
        return jsonReader.getCharacter(json.getJSONObject(0))
    }

    fun getByGender(gender: String, amount: Int = 0): List<Character> {
        var url = "${BASE_URL}${urlPath}?gender=$gender"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getByAge(age: Int, amount: Int = 0): List<Character> {
        var url = "${BASE_URL}${urlPath}?age=$age"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getByHair(hair: String, amount: Int = 0): List<Character> {
        var url = "${BASE_URL}${urlPath}?hair=$hair"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getByOccupation(occupation: String, amount: Int = 0): List<Character> {
        var url = "${BASE_URL}${urlPath}?occupation=$occupation"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getByVoiceActor(voiceActor: VoiceActor, amount: Int = 0): List<Character> {
        var url = "${BASE_URL}${urlPath}?voicedBy=${voiceActor.name}"
        if (amount > 0) url += "&limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

}
