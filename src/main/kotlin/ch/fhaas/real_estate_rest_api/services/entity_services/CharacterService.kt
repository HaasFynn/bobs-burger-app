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
        get("?name=$name")

    fun getByGender(gender: String, amount: Int = 0): List<Character> =
        getAmount("?gender=$gender", amount)

    fun getByAge(age: Int, amount: Int = 0): List<Character> =
        getAmount("?age=$age", amount)

    fun getByHair(hair: String, amount: Int = 0): List<Character> =
        getAmount("?hair=$hair", amount)

    fun getByOccupation(occupation: String, amount: Int = 0): List<Character> =
        getAmount("?occupation=$occupation", amount)

    fun getByVoiceActor(voiceActor: VoiceActor, amount: Int = 0): List<Character> =
        getAmount("?voicedBy=${voiceActor.name}", amount)

}
