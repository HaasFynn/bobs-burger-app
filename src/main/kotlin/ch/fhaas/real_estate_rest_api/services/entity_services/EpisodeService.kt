package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.entity.Episode
import ch.fhaas.real_estate_rest_api.entity.Season
import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestClient
import ch.fhaas.real_estate_rest_api.services.ResultHandler
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONObject
import org.springframework.stereotype.Service

@Service
class EpisodeService(requestClient: RequestClient) : EntityService(requestClient, "/episode") {

    fun getByProductionCode(productionCode: String): JSONObject =
        get("?productionCode=$productionCode", 0).getJSONObject(0)

    fun getByAirDate(date: String, amount: Int = 0): JSONArray =
        get("?airDate=$date", amount)

    fun getBySeason(season: Season, amount: Int = 0): JSONArray =
        get("?season=${season.seasonNum}", amount)

    fun getBySeason(season: Int, amount: Int = 0): JSONArray =
        get("?season=$season", amount)

    fun getByIndex(num: Int): JSONObject =
        get("?num=$num", 0).getJSONObject(0)

    fun getByTotalViewers(totalViewers: Int, amount: Int = 0): JSONArray =
        get("?totalViewers=$totalViewers", amount)
}