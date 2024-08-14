package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.entity.Burger
import ch.fhaas.real_estate_rest_api.entity.Episode
import ch.fhaas.real_estate_rest_api.entity.Season
import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestClient
import ch.fhaas.real_estate_rest_api.services.ResultHandler
import org.json.JSONArray
import org.springframework.stereotype.Service

@Service
class BurgerService(
    jsonReader: JsonReader,
    resultHandler: ResultHandler,
    requestClient: RequestClient
) : EntityService<Burger>(jsonReader, resultHandler, requestClient, "burger", jsonReader::getBurger) {

    fun getByName(name: String): Burger? {
        val url = "${BASE_URL}${urlPath}?name=$name"
        val json: JSONArray = request(url)
        return jsonReader.getBurger(json.getJSONObject(0))
    }

    fun getByPrice(price: Double): List<Burger> {
        val url = "${BASE_URL}${urlPath}?price=$price"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getBySeason(season: Season): List<Burger> {
        val url = "${BASE_URL}${urlPath}?season=${season.seasonNum}"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getByEpisode(episode: Episode): List<Burger> {
        val url = "${BASE_URL}${urlPath}?episode=${episode.episodeNum}"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }
}