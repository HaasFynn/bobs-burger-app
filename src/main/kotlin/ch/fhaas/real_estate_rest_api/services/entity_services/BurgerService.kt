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
    jsonReader: JsonReader, resultHandler: ResultHandler, requestClient: RequestClient
) : EntityService<Burger>(jsonReader, resultHandler, requestClient) {
    override val getAction = jsonReader::getResultOfBurger

    companion object {
        private const val ENDING = "burger"
    }

    override fun get(amount: Int): List<Burger> {
        var url: String = BASE_URL + ENDING
        if (amount > 0) url += "?limit=$amount"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    override fun getAll(): List<Burger> {
        val url: String = BASE_URL + ENDING
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getByName(name: String): Burger? {
        val url = "${BASE_URL}${ENDING}?name=$name"
        val json: JSONArray = request(url)
        val result: Result<Burger> = jsonReader.getResultOfBurger(json.getJSONObject(0))
        return resultHandler.getEntityOfResult(result)
    }

    fun getByPrice(price: Double): List<Burger> {
        val url = "${BASE_URL}${ENDING}?price=$price"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getBySeason(season: Season): List<Burger> {
        val url = "${BASE_URL}${ENDING}?season=${season.seasonNum}"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }

    fun getByEpisode(episode: Episode): List<Burger> {
        val url = "${BASE_URL}${ENDING}?episode=${episode.episodeNum}"
        val json: JSONArray = request(url)
        return getListOfEntity(json, getAction = getAction)
    }
}