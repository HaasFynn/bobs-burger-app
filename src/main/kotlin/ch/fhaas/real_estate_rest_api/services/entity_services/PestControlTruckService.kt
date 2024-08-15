package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.entity.Episode
import ch.fhaas.real_estate_rest_api.entity.PestControlTruck
import ch.fhaas.real_estate_rest_api.entity.Season
import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestClient
import ch.fhaas.real_estate_rest_api.services.ResultHandler
import org.springframework.stereotype.Service

@Service
class PestControlTruckService(
    jsonReader: JsonReader,
    resultHandler: ResultHandler,
    requestClient: RequestClient,
): EntityService<PestControlTruck>(jsonReader, resultHandler, requestClient, "pestControlTruck", jsonReader::getPestControlTruck) {

    fun getByName(name: String): PestControlTruck? =
        get("?name=$name")

    fun getBySeason(season: Season, amount: Int = 0): List<PestControlTruck> =
        getAmount(amount, "?season=${season.seasonNum}")

    fun getBySeason(season: Int, amount: Int = 0): List<PestControlTruck> =
        getAmount(amount, "?season=$season")

    fun getByEpisode(episode: Episode): PestControlTruck? =
        get("?episode=${episode.episodeNum}")

    fun getByEpisode(episode: Int): PestControlTruck? =
        get("?episode=${episode}")


}
