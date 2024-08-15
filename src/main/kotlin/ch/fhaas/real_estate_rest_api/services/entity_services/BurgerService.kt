package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.entity.Burger
import ch.fhaas.real_estate_rest_api.entity.Episode
import ch.fhaas.real_estate_rest_api.entity.Season
import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestClient
import ch.fhaas.real_estate_rest_api.services.ResultHandler
import org.springframework.stereotype.Service

@Service
class BurgerService(
    jsonReader: JsonReader,
    resultHandler: ResultHandler,
    requestClient: RequestClient
) : EntityService<Burger>(jsonReader, resultHandler, requestClient, "burgerOfTheDay", jsonReader::getBurger) {

    fun getByName(name: String): Burger? =
        get("?name=$name")

    fun getByPrice(price: Double, amount: Int = 0): List<Burger> =
        getAmount(amount, "?price=$price")

    fun getBySeason(season: Season, amount: Int): List<Burger> =
        getAmount(amount, "?season=${season.seasonNum}")

    fun getBySeason(season: Int, amount: Int): List<Burger> =
        getAmount(amount, "?season=${season}")

    fun getByEpisode(episode: Episode): Burger? =
        get("?episode=${episode.episodeNum}")

    fun getByEpisode(episode: Int): Burger? =
        get("?episode=${episode}")
}
