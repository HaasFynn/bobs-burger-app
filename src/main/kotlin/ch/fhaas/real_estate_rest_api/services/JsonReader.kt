package ch.fhaas.real_estate_rest_api.services

import ch.fhaas.real_estate_rest_api.entity.*
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.stereotype.Service


@Service
class JsonReader(private val resultHandler: ResultHandler) {

    fun getCharacter(json: JSONObject): Result<Character> = getResultOfEntity(
            Character(
                name = json.getString("name"),
                wikiUrl = json.getString("wikiUrl"),
                relatives = getRelatives(json.getJSONArray("relatives")),
                imageUrl = json.getString("imageUrl"),
                gender = json.getString("gender"),
                age = json.getInt("age"),
                hair = json.getString("hair"),
                occupation = getCurrentOccupation(json.getJSONObject("occupation")),
                allOccupations = getOccupations(json.getJSONArray("allOccupations")),
                firstEpisode = getFirstEpisode(json.getJSONObject("firstEpisode")),
                voicedBy = getVoiceActor(json.getJSONObject("voicedBy"))
            )
    )

    private fun getVoiceActor(json: JSONObject): VoiceActor? = resultHandler.getEntityOfResult(
        getResultOfVoiceActor(json)
    )

    private fun getFirstEpisode(json: JSONObject): Episode? = resultHandler.getEntityOfResult(
        getResultOfEpisode(json)
    )

    private fun getOccupations(json: JSONArray): List<Occupation> = resultHandler.getListOfResult(
        getResultOfArray(json, this::getResultOfOccupation)
    )

    private fun getCurrentOccupation(json: JSONObject): Occupation? = resultHandler.getEntityOfResult(
        getResultOfOccupation(json)
    )

    private fun getRelatives(json: JSONArray): List<Relative> = resultHandler.getListOfResult(
        getResultOfArray(json, this::getRelative),
    )

    fun getResultOfVoiceActor(json: JSONObject): Result<VoiceActor> = getResultOfEntity(
            VoiceActor(
                name = json.getString("name"),
            )
    )

    fun getResultOfEpisode(json: JSONObject): Result<Episode> = getResultOfEntity(
            Episode(
                name = json.getString("name"),
                wikiUrl = json.getString("wikiUrl"),
                productionCode = json.getString("productionCode"),
                airDate = json.getString("airDate"),
                episodeNum = json.getInt("episode"),
                totalViewers = json.getInt("totalViewers")
            )
    )

    fun getSeason(json: JSONObject): Result<Season> = getResultOfEntity(
        Season(
            seasonNum = json.getInt("season"), episodes = emptyList() //TODO: Get all Episodes of season
        )
    )

    fun getRelative(json: JSONObject): Result<Relative> = getResultOfEntity(
            Relative(
                name = json.getString("name"),
                wikiUrl = json.getString("wikiUrl"),
                relation = json.getString("relationship")
            )
    )

    fun getResultOfOccupation(json: JSONObject): Result<Occupation> = getResultOfEntity(
            Occupation(
                name = json.getString("name"),
            )
    )

    fun <T : Entity> getResultOfEntity(entity: T): Result<T> = try {
        Result.success(entity)
    } catch (e: NullPointerException) {
        Result.failure(e)
        }


    fun <T : Entity> getResultOfArray(json: JSONArray, getEntity: (obj: JSONObject) -> (Result<T>)): List<Result<T>> =
        (0 until json.length()).fold(emptyList()) { list, index ->
            val obj = json.getJSONObject(index)
            val result: Result<T> = getEntity(obj)
            return list + result
        }

    fun getResultOfBurger(jsonObject: JSONObject): Result<Burger> {
        TODO("Not yet implemented")
    }
}