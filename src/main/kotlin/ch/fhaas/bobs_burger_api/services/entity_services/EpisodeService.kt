package ch.fhaas.bobs_burger_api.services.entity_services

import ch.fhaas.bobs_burger_api.entity.Season
import ch.fhaas.bobs_burger_api.services.RequestClient
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONObject
import org.springframework.stereotype.Service

@Service
class EpisodeService(requestClient: RequestClient) : EntityService(requestClient, "episodes") {

    fun getByName(name: String): JSONObject {
        val json = get("?name=${name.replace("\"", "%22")}", 0)
        if (json.isEmpty) return JSONObject()
        return json.getJSONObject(0)
    }

    fun getByProductionCode(productionCode: String): JSONObject {
        val json = get("?productionCode=$productionCode", 0)
        if (json.isEmpty) return JSONObject()
        return json.getJSONObject(0)
    }
    fun getByAirDate(date: String, amount: Int = 0): JSONArray =
        get("?airDate=$date", amount)

    fun getBySeason(season: Season, amount: Int = 0): JSONArray =
        get("?season=${season.seasonNum}", amount)

    fun getBySeason(season: Int, amount: Int = 0): JSONArray =
        get("?season=$season", amount)

    fun getByIndex(num: Int): JSONObject {
        val json = get("?episode=$num", 0)
        if (json.isEmpty) return JSONObject()
        return json.getJSONObject(0)
    }

    fun getByTotalViewers(totalViewers: Int, amount: Int = 0): JSONArray =
        get("?totalViewers=$totalViewers", amount)
}