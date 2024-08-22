package ch.fhaas.bobs_burger_api.services.entity_services

import ch.fhaas.bobs_burger_api.entity.Episode
import ch.fhaas.bobs_burger_api.entity.Season
import ch.fhaas.bobs_burger_api.services.RequestBuilder
import ch.fhaas.bobs_burger_api.services.RequestClient
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONObject
import org.springframework.stereotype.Service

@Service
class StoreNextDoorService(
    requestClient: RequestClient,
    requestBuilder: RequestBuilder
) : EntityService(requestClient, requestBuilder, "storeNextDoor") {

    fun getByName(name: String): JSONObject =
        get(Pair("name", name), 0).getJSONObject(0)

    fun getBySeason(season: Season, amount: Int = 0): JSONArray =
        get(Pair("season", season.seasonNum), amount)

    fun getBySeason(season: Int, amount: Int = 0): JSONArray =
        get(Pair("season", season), amount)

    fun getByEpisode(episode: Episode): JSONArray =
        get(Pair("episode", episode.episodeNum), 0)

    fun getByEpisode(episode: Int): JSONArray =
        get(Pair("episode", episode), 0)
}