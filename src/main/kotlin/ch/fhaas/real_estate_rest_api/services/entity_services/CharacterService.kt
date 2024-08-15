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
) : EntityService<Character>(jsonReader, resultHandler, requestClient, "characters", jsonReader::getCharacter) {

    fun getByName(name: String): Character? =
        get("?name=$name")

    fun getByGender(gender: String, amount: Int = 0): List<Character> =
        getAmount(amount, "?gender=$gender")

    fun getByAge(age: Int, amount: Int = 0): List<Character> =
        getAmount(amount, "?age=$age")

    fun getByHair(hair: String, amount: Int = 0): List<Character> =
        getAmount(amount, "?hair=$hair")

    fun getByOccupation(occupation: String, amount: Int = 0): List<Character> =
        getAmount(amount, "?occupation=$occupation")

    fun getByVoiceActor(voiceActor: VoiceActor, amount: Int = 0): List<Character> =
        getAmount(amount, "?voicedBy=${voiceActor.name}")

}
