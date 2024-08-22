package ch.fhaas.bobs_burger_api.services

import ch.fhaas.bobs_burger_api.entity.*
import ch.fhaas.bobs_burger_api.printWarning
import ch.fhaas.bobs_burger_api.services.entity_services.EpisodeService
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONException
import kong.unirest.core.json.JSONObject
import org.springframework.stereotype.Service

@Service
class JsonReader(private val episodeService: EpisodeService) {

    fun getCharacter(json: JSONObject): Character? =
            Character(
                name = getString(json, "name"),
                wikiUrl = getString(json, "wikiUrl"),
                relatives = getObjectsOf(json.getJSONArray("relatives"), this::getRelative),
                imageUrl = getString(json, "image"),
                gender = getString(json, "gender"),
                age = getInt(json, "age"),
                hair = getString(json, "hair"),
                occupation = getOccupation(getString(json, "occupation")),
                allOccupations = getListOf(getArray(json, "allOccupations"), this::getOccupation),
                firstEpisode = requestEpisode(getString(json, "firstEpisode")),
                voicedBy = getVoiceActor(json)
            )

    fun getVoiceActor(json: JSONObject): VoiceActor? =
            VoiceActor(
                name = getString(json, "voicedBy"),
            )

    fun getEpisode(json: JSONObject): Episode? =
            Episode(
                name = getString(json, "name"),
                wikiUrl = getString(json, "wikiUrl"),
                productionCode = getString(json, "productionCode"),
                airDate = getString(json, "airDate"),
                season = getSeason(json),
                episodeNum = getInt(json, "episode"),
                totalViewers = getString(json, "totalViewers")
            )

    fun getSeason(json: JSONObject): Season? =
        Season(
            seasonNum = getInt(json, "season")
        )

    fun getRelative(json: JSONObject): Relative =
            Relative(
                name = getString(json, "name"),
                wikiUrl = getString(json, "wikiUrl"),
                relation = getString(json, "relationship")
            )

    fun getOccupation(name: String): Occupation? =
        Occupation(
            name = name,
        )

    fun getBurger(json: JSONObject): Burger? =
        Burger(
            name = getString(json, "name"),
            wikiUrl = getString(json, "wikiUrl"),
            price = getString(json, "price"),
            season = getSeason(json),
            episode = requestEpisode(getInt(json, "episode"))
        )

    fun getPestControlTruck(json: JSONObject): PestControlTruck? =
        PestControlTruck(
            name = getString(json, "name"),
            wikiUrl = getString(json, "url"),
            season = getSeason(json),
            episode = requestEpisode(getInt(json, "episode")),
            imageUrl = getString(json, "image")
        )

    fun getStoreNextDoor(json: JSONObject): StoreNextDoor? =
        StoreNextDoor(
            name = getString(json, "name"),
            wikiUrl = getString(json, "url"),
            firstSeason = getSeason(json),
            firstEpisode = requestEpisode(getInt(json, "episode")),
            imageUrl = getString(json, "image")
        )


    private fun requestEpisode(index: Int): Episode? {
        val json: JSONObject = episodeService.getByIndex(index)
        return getEpisode(json)
    }

    private fun requestEpisode(name: String): Episode? {
        val json: JSONObject = episodeService.getByName(name)
        return getEpisode(json)
    }

    fun <T : Entity> getObjectsOf(json: JSONArray, getEntity: (obj: JSONObject) -> (T?)): List<T> =
        (0 until json.length()).fold(emptyList()) { list, index ->
            val obj = json.getJSONObject(index)
            val entity: T? = getEntity(obj)
            entity?.let { list + entity } ?: list
        }

    fun <T : Entity> getListOf(json: JSONArray, getEntity: (obj: String) -> (T?)): List<T> =
        (0 until json.length()).fold(emptyList()) { list, index ->
            val obj = json.getString(index)
            val entity: T? = getEntity(obj)
            entity?.let { list + entity } ?: list
        }

    private fun getString(json: JSONObject, key: String): String =
        try {
            json.getString(key)
        } catch (e: JSONException) {
            printWarning("Was not able to get $key")
            ""
        }

    private fun getInt(json: JSONObject, key: String): Int =
        try {
            json.getInt(key)
        } catch (e: JSONException) {
            printWarning("Was not able to get $key")
            0
        }

    private fun getObject(json: JSONObject, key: String): JSONObject =
        try {
            json.getJSONObject(key)
        } catch (e: JSONException) {
            printWarning("Was not able to get $key")
            JSONObject()
        }

    private fun getArray(json: JSONObject, key: String): JSONArray =
        try {
            json.getJSONArray(key)
        } catch (e: JSONException) {
            printWarning("Was not able to get $key")
            JSONArray()
        }
}
