package ch.fhaas.bobs_burger_api.services.entity_services

import ch.fhaas.bobs_burger_api.entity.VoiceActor
import ch.fhaas.bobs_burger_api.services.RequestBuilder
import ch.fhaas.bobs_burger_api.services.RequestClient
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONObject
import org.springframework.stereotype.Service

@Service
class CharacterService(
    requestClient: RequestClient,
    requestBuilder: RequestBuilder
) : EntityService(requestClient, requestBuilder, "characters") {

    fun getByName(name: String): JSONObject {
        val debug = get(Pair("name", name), 0).getJSONObject(0)
        return debug
    }

    fun getByGender(gender: String, amount: Int = 0): JSONArray =
        get(Pair("gender", gender), amount)

    fun getByAge(age: Int, amount: Int = 0): JSONArray =
        get(Pair("age", age), amount)

    fun getByHair(hair: String, amount: Int = 0): JSONArray =
        get(Pair("hair", hair), amount)

    fun getByOccupation(occupation: String, amount: Int = 0): JSONArray =
        get(Pair("occupation", occupation), amount)

    fun getByVoiceActor(voiceActor: VoiceActor, amount: Int = 0): JSONArray =
        get(Pair("voiceActor", voiceActor.name), amount)
}