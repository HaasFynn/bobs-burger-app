package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.entity.Character
import ch.fhaas.real_estate_rest_api.entity.VoiceActor
import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestClient
import ch.fhaas.real_estate_rest_api.services.ResultHandler
import org.springframework.stereotype.Service

@Service
class CharacterService(
    jsonReader: JsonReader, resultHandler: ResultHandler, requestClient: RequestClient
) : EntityService<Character>(jsonReader, resultHandler, requestClient, "character", jsonReader::getCharacter) {

    fun getByName(name: String): Character? =
        get("${BASE_URL}${urlPath}?name=$name")

    fun getByGender(gender: String, amount: Int = 0): List<Character> =
        getAmount("${BASE_URL}${urlPath}?gender=$gender", amount)

    fun getByAge(age: Int, amount: Int = 0): List<Character> =
        getAmount("${BASE_URL}${urlPath}?age=$age", amount)

    fun getByHair(hair: String, amount: Int = 0): List<Character> =
        getAmount("${BASE_URL}${urlPath}?hair=$hair", amount)

    fun getByOccupation(occupation: String, amount: Int = 0): List<Character> =
        getAmount("${BASE_URL}${urlPath}?occupation=$occupation", amount)


    fun getByVoiceActor(voiceActor: VoiceActor, amount: Int = 0): List<Character> =
        getAmount("${BASE_URL}${urlPath}?voicedBy=${voiceActor.name}", amount)

}
