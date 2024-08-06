package ch.fhaas.real_estate_rest_api

import ch.fhaas.real_estate_rest_api.dao.*
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.request.GetRequest
import org.springframework.stereotype.Service
import java.util.HashMap

@Service
class Requester(
    private val characterRepo: CharacterRepository,
    private val burgerOfTheDayRepo: BurgerOfTheDayRepository,
    private val episodeRepo: EpisodeRepository,
    private val storeNextDoorRepo: StoreNextDoorRepository,
    private val pestControlTruckRepo: PestControlTruckRepository,
    private val endCreditsSequenceRepo: EndCreditsSequenceRepository
) {

    private fun request(url: String, headers: HashMap<String, String>): HttpResponse<String> {
        val request: GetRequest = Unirest.get(url);
        request.headers(headers)
        return request.asString()
    }

}