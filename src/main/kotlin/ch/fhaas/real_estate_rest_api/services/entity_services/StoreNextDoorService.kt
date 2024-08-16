package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.entity.Episode
import ch.fhaas.real_estate_rest_api.entity.Season
import ch.fhaas.real_estate_rest_api.services.RequestClient
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONObject
import org.springframework.stereotype.Service

@Service
class StoreNextDoorService(requestClient: RequestClient) : EntityService(requestClient, "/storeNextDoor") {

    fun getByName(name: String): JSONObject =
        get("?name=$name", 0).getJSONObject(0)

    fun getBySeason(season: Season, amount: Int = 0): JSONArray =
        get("?season=${season.seasonNum}", amount)

    fun getBySeason(season: Int, amount: Int = 0): JSONArray =
        get("?season=$season", amount)

    fun getByEpisode(episode: Episode): JSONArray =
        get("?episode=${episode.episodeNum}", 0)

    fun getByEpisode(episode: Int): JSONArray =
        get("?episode=${episode}", 0)
}