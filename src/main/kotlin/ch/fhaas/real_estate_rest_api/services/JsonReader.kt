package ch.fhaas.real_estate_rest_api.services

import ch.fhaas.real_estate_rest_api.entity.*
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONObject
import org.springframework.stereotype.Service


@Service
class JsonReader(private val resultHandler: ResultHandler) {

    fun getCharacter(json: JSONObject): Character? =
            Character(
                name = json.getString("name"),
                wikiUrl = json.getString("url"),
                relatives = getListOf(json.getJSONArray("relatives"), this::getRelative),
                imageUrl = json.getString("image"),
                gender = json.getString("gender"),
                age = json.getInt("age"),
                hair = json.getString("hair"),
                occupation = getOccupation(json.getJSONObject("occupation")),
                allOccupations = getListOf(json.getJSONArray("allOccupations"), this::getOccupation),
                firstEpisode = getEpisode(json.getJSONObject("firstEpisode")),
                voicedBy = getVoiceActor(json.getJSONObject("voicedBy"))
            )

    fun getVoiceActor(json: JSONObject): VoiceActor? =
            VoiceActor(
                name = json.getString("name"),
            )

    fun getEpisode(json: JSONObject): Episode? =
            Episode(
                name = json.getString("name"),
                wikiUrl = json.getString("url"),
                productionCode = json.getString("productionCode"),
                airDate = json.getString("airDate"),
                season = getSeason(json),
                episodeNum = json.getInt("episode"),
                totalViewers = json.getInt("totalViewers")
            )


    fun getSeason(json: JSONObject): Season? =
        Season(
            seasonNum = json.getInt("season")
        )


    fun getRelative(json: JSONObject): Relative =
            Relative(
                name = json.getString("name"),
                wikiUrl = json.getString("url"),
                relation = json.getString("relationship")
            )


    fun getOccupation(json: JSONObject): Occupation? =
            Occupation(
                name = json.getString("name"),
            )


    fun getBurger(json: JSONObject): Burger? =
        Burger(
            name = json.getString("name"),
            wikiUrl = json.getString("url"),
            price = getPrice(json),
            season = getSeason(json),
            episode = getEpisode(json)
        )

    private fun getPrice(json: JSONObject): Double = json.getString("price")
        .replace("$", "")
        .toDouble()

    fun getPestControlTruck(json: JSONObject): PestControlTruck? =
        PestControlTruck(
            name = json.getString("name"),
            wikiUrl = json.getString("url"),
            season = getSeason(json),
            episode = getEpisode(json),
            imageUrl = json.getString("image")
        )

    fun getStoreNextDoor(json: JSONObject): StoreNextDoor? =
        StoreNextDoor(
            name = json.getString("name"),
            wikiUrl = json.getString("url"),
            firstSeason = getSeason(json),
            firstEpisode = getEpisode(json),
            imageUrl = json.getString("image")
        )

    fun <T : Entity> getResultOfEntity(entity: T): Result<T> = try {
        Result.success(entity)
    } catch (e: NullPointerException) {
        Result.failure(e)
    }

    fun <T : Entity> getListOf(json: JSONArray, getEntity: (obj: JSONObject) -> (T?)): List<T> =
        (0 until json.length()).fold(emptyList()) { list, index ->
            val obj = json.getJSONObject(index)
            val entity: T? = getEntity(obj)
            entity?.let { list + entity } ?: list
        }


    fun <T : Entity> getResultOfArray(json: JSONArray, getEntity: (obj: JSONObject) -> (Result<T>)): List<Result<T>> =
        (0 until json.length()).fold(emptyList()) { list, index ->
            val obj = json.getJSONObject(index)
            val result: Result<T> = getEntity(obj)
            return list + result
        }
}
