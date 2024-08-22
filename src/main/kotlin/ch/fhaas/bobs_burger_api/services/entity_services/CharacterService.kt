package ch.fhaas.bobs_burger_api.services.entity_services

import ch.fhaas.bobs_burger_api.entity.VoiceActor
import ch.fhaas.bobs_burger_api.services.RequestClient
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONObject
import org.springframework.stereotype.Service

@Service
class CharacterService(requestClient: RequestClient) : EntityService(requestClient, "characters") {

    fun getByName(name: String): JSONObject {
        val debug = get("?name=$name", 0).getJSONObject(0)
        return debug
    }

    fun getByGender(gender: String, amount: Int = 0): JSONArray =
        get("?gender=$gender", amount)

    fun getByAge(age: Int, amount: Int = 0): JSONArray =
        get("?age=$age", amount)

    fun getByHair(hair: String, amount: Int = 0): JSONArray =
        get("?hair=$hair", amount)

    fun getByOccupation(occupation: String, amount: Int = 0): JSONArray =
        get("?occupation=$occupation", amount)

    fun getByVoiceActor(voiceActor: VoiceActor, amount: Int = 0): JSONArray =
        get("?voicedBy=${voiceActor.name}", amount)
}