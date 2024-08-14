package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.entity.Episode
import ch.fhaas.real_estate_rest_api.entity.Season
import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestClient
import ch.fhaas.real_estate_rest_api.services.ResultHandler
import org.springframework.stereotype.Service

@Service
class EpisodeService(
    jsonReader: JsonReader,
    resultHandler: ResultHandler,
    requestClient: RequestClient,
) : EntityService<Episode>(jsonReader, resultHandler, requestClient, "episode", jsonReader::getEpisode) {

    fun getByProductionCode(productionCode: String): Episode? =
        get("?productionCode=$productionCode")


    fun getByAirDate(date: String, amount: Int = 0): List<Episode> =
        getAmount("?airDate=$date", amount)

    fun getBySeason(season: Season, amount: Int = 0): List<Episode> =
        getAmount("?season=${season.seasonNum}", amount)

    fun getBySeason(season: Int, amount: Int = 0): List<Episode> =
        getAmount("?season=$season", amount)

    fun getByIndex(num: Int): Episode? =
        get("?num=$num")

    fun getByTotalViewers(totalViewers: Int, amount: Int = 0): List<Episode> =
        getAmount("?totalViewers=$totalViewers", amount)

}