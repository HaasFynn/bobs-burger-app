package ch.fhaas.bobs_burger_api.services.entity_services

import ch.fhaas.bobs_burger_api.entity.Episode
import ch.fhaas.bobs_burger_api.entity.Season
import ch.fhaas.bobs_burger_api.services.RequestClient
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONObject
import org.springframework.stereotype.Service

@Service
class PestControlTruckService(requestClient: RequestClient) : EntityService(requestClient, "pestControlTruck") {

    fun getByName(name: String): JSONObject =
        get("?name=$name", 0).getJSONObject(0)

    fun getBySeason(season: Season, amount: Int = 0): JSONArray =
        get("?season=${season.seasonNum}", amount)

    fun getBySeason(season: Int, amount: Int = 0): JSONArray =
        get("?season=$season", amount)

    fun getByEpisode(episode: Episode): JSONObject =
        get("?episode=${episode.episodeNum}", 0).getJSONObject(0)

    fun getByEpisode(episode: Int): JSONObject =
        get("?episode=${episode}", 0).getJSONObject(0)
}
