package ch.fhaas.bobs_burger_api.services.entity_services

import ch.fhaas.bobs_burger_api.entity.Episode
import ch.fhaas.bobs_burger_api.entity.Season
import ch.fhaas.bobs_burger_api.services.RequestClient
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONObject
import org.springframework.stereotype.Service

@Service
class BurgerService(
    requestClient: RequestClient
) : EntityService(requestClient, "burgerOfTheDay") {

    fun getByName(name: String): JSONObject =
        get("?name=$name", 0).getJSONObject(0)

    fun getByPrice(price: Double, amount: Int = 0): JSONArray =
        get("?price=$price", amount)

    fun getBySeason(season: Season, amount: Int): JSONArray =
        get("?season=${season.seasonNum}", amount)

    fun getBySeason(season: Int, amount: Int): JSONArray =
        get("?season=${season}", amount)

    fun getByEpisode(episode: Episode): JSONObject =
        get("?episode=${episode.episodeNum}", 0).getJSONObject(0)

    fun getByEpisode(episode: Int): JSONObject =
        get("?episode=${episode}", 0).getJSONObject(0)
}
