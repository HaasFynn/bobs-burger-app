package ch.fhaas.real_estate_rest_api.services

import ch.fhaas.real_estate_rest_api.entity.*
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.stereotype.Service


@Service
class JsonReader(private val resultHandler: ResultHandler) {

    fun getCharacter(json: JSONObject): Result<Character> =
        runCatching {
            Character(
                name = json.getString("name"),
                wikiUrl = json.getString("wikiUrl"),
                relatives = resultHandler.getListOfResult(
                    getArray(json.getJSONArray("relatives"), this::getRelative)
                ),
                imageUrl = json.getString("imageUrl"),
                gender = json.getString("gender"),
                age = json.getInt("age"),
                hair = json.getString("hair"),
                occupation = resultHandler.getEntityOfResult(
                    getOccupation(json.getJSONObject("occupation"))
                ),
                allOccupations = resultHandler.getListOfResult(
                    getArray(json.getJSONArray("allOccupations"), this::getOccupation)
                ),
                firstEpisode = resultHandler.getEntityOfResult(
                    getEpisode(json.getJSONObject("firstEpisode"))
                ),
                voicedBy = resultHandler.getEntityOfResult(
                    getVoiceActor(json.getJSONObject("voicedBy"))
                )
            )
        }

    fun getVoiceActor(json: JSONObject): Result<VoiceActor> =
        runCatching {
            VoiceActor(
                name = json.getString("name"),
            )
        }

    fun getEpisode(json: JSONObject): Result<Episode> =
        runCatching {
            Episode(
                name = json.getString("name"),
                wikiUrl = json.getString("wikiUrl"),
                productionCode = json.getString("productionCode"),
                airDate = json.getString("airDate"),
                episodeNum = json.getInt("episode"),
                totalViewers = json.getInt("totalViewers")
            )
        }

    fun getSeason(json: JSONObject): Result<Season> = runCatching {
        Season(
            seasonNumber = json.getInt("season"), episodes = emptyList() //TODO: Get all Episodes of season
        )
    }

    fun getRelative(json: JSONObject): Result<Relative> =
        runCatching {
            Relative(
                name = json.getString("name"),
                wikiUrl = json.getString("wikiUrl"),
                relation = json.getString("relationship")
            )
        }

    fun getOccupation(json: JSONObject): Result<Occupation> =
        runCatching {
            Occupation(
                name = json.getString("name"),
            )
        }


    fun <T : Entity> getArray(json: JSONArray, getEntity: (obj: JSONObject) -> (Result<T>)): List<Result<T>> =
        (0 until json.length()).fold(emptyList()) { list, index ->
            val obj = json.getJSONObject(index)
            val result: Result<T> = getEntity(obj)
            return list + result
        }
}