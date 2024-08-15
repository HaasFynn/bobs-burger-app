package ch.fhaas.real_estate_rest_api.services.entity_services

import ch.fhaas.real_estate_rest_api.entity.Episode
import ch.fhaas.real_estate_rest_api.entity.Season
import ch.fhaas.real_estate_rest_api.entity.StoreNextDoor
import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestClient
import ch.fhaas.real_estate_rest_api.services.ResultHandler
import org.springframework.stereotype.Service

@Service
class StoreNextDoorService(
    jsonReader: JsonReader,
    resultHandler: ResultHandler,
    requestClient: RequestClient,
): EntityService<StoreNextDoor>(jsonReader, resultHandler, requestClient, "storeNextDoor", jsonReader::getStoreNextDoor) {

    fun getByName(name: String): StoreNextDoor? =
        get("?name=$name")

    fun getBySeason(season: Season, amount: Int = 0): List<StoreNextDoor> =
        getAmount(amount, "?season=${season.seasonNum}")

    fun getBySeason(season: Int, amount: Int = 0): List<StoreNextDoor> =
        getAmount(amount, "?season=$season")

    fun getByEpisode(episode: Episode): StoreNextDoor? =
        get("?episode=${episode.episodeNum}")

    fun getByEpisode(episode: Int): StoreNextDoor? =
        get("?episode=${episode}")

}