package ch.fhaas.real_estate_rest_api

import ch.fhaas.real_estate_rest_api.entity.*
import ch.fhaas.real_estate_rest_api.services.entity_services.BurgerService
import ch.fhaas.real_estate_rest_api.services.entity_services.CharacterService
import ch.fhaas.real_estate_rest_api.services.entity_services.EpisodeService
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.*

@RestController("/")
@ComponentScan
class RequestHandler(
    private val characterService: CharacterService,
    private val episodeService: EpisodeService,
    private val burgerService: BurgerService
) {


    //TODO: LATER APPROACH: Implement Requester.kt to Request Data from other API's and convert Data to own format. Add @Service Annotation
    // ETL Pipeline with *Spring Cloud Task* -> https://www.baeldung.com/spring-cloud-data-flow-etl

    companion object {
        private const val UNCOMPLETE_LIST_HEADER_RESPONSE: String = "was not able to get all wanted entries"
    }

    @RequestMapping("/")
    @ResponseBody
    private fun main(): Any {
        val url: String = "http://localhost8080/"
        return """
            |<h1>Bob's Burger App</h1>
            |Possible Requests:
            |<ul>
            |<li><a href="${url}characters">Characters</a></li>
            |<li><a href="${url}episodes>Episodes</a></li>
            |<li><a href="${url}burgers>Burgers</a></li>
            |<li><a href="${url}storeNextDoor>StoreNextDoor</a></li>
            |<li><a href="${url}pestControlTruck>PestControlTruck</a></li>
            |</ul>
        """.trimMargin()
    }

    @GetMapping("/test")
    @ResponseBody
    private fun helloWorld(): Any {
        return "<h1>Test Failed. Pls contact support!</h1>"
    }

    @GetMapping(
        "/characters",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getCharacter(
        @RequestParam(name = "amount", defaultValue = "0", required = false)
        amount: Int,
        @RequestParam(name = "name", defaultValue = "", required = false)
        name: String,
        @RequestParam(name = "gender", defaultValue = "", required = false)
        gender: String,
        @RequestParam(name = "age", defaultValue = "0", required = false)
        age: Int,
        @RequestParam(name = "hair", defaultValue = "", required = false)
        hair: String,
        @RequestParam(name = "currentOccupation", defaultValue = "", required = false)
        currentOccupation: String,
        @RequestParam(name = "occupation", defaultValue = "", required = false)
        occupation: String,
        @RequestParam(name = "episode", defaultValue = "0", required = false)
        episode: Int,
        @RequestParam(name = "voiceActor", defaultValue = "", required = false)
        voiceActor: String
    ): List<Character> = characterService.getAll()


    @GetMapping(
        "/episodes",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getEpisode(
        @RequestParam(name = "amount", defaultValue = "0", required = false)
        amount: Int,
        @RequestParam(name = "name", defaultValue = "", required = false)
        name: String,
        @RequestParam(name = "productionCode", defaultValue = "", required = false)
        productionCode: String,
        @RequestParam(name = "airDate", defaultValue = "", required = false)
        airDate: String,
        @RequestParam(name = "seasonNum", defaultValue = "0", required = false)
        seasonNum: Int,
        @RequestParam(name = "episode", defaultValue = "0", required = false)
        episodeNum: Int,
        @RequestParam(name = "totalViewer", defaultValue = "", required = false)
        occupation: String
    ): List<Episode> = episodeService.getAll()


    @GetMapping(
        "/burgers",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getBurgers(
        @RequestParam(name = "amount", defaultValue = "0", required = false)
        amount: Int,
        @RequestParam(name = "name", defaultValue = "", required = false)
        name: String,
        @RequestParam(name = "price", defaultValue = "0", required = false)
        price: Double,
        @RequestParam(name = "seasonNum", defaultValue = "0", required = false)
        seasonNum: Int,
        @RequestParam(name = "episode", defaultValue = "0", required = false)
        episodeNum: Int
    ): List<Burger> = burgerService.getAll()

    @GetMapping(
        "/storeNextDoor",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getStoreNextDoor(
        @RequestParam(name = "amount", required = false)
        amount: Int,
        @RequestParam(name = "name", required = false)
        name: String,
        @RequestParam(name = "seasonNum", required = false)
        seasonNum: Int,
        @RequestParam(name = "episode", required = false)
        episodeNum: Int
    ): List<StoreNextDoor> {
        TODO("Implement me")
    }

    @GetMapping(
        "/pestControlTruck",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getPestControlTruck(
        @RequestParam(name = "amount", required = false)
        amount: Int,
        @RequestParam(name = "name", required = false)
        name: String,
        @RequestParam(name = "seasonNum", required = false)
        seasonNum: Int,
        @RequestParam(name = "episode", required = false)
        episodeNum: Int
    ): List<PestControlTruck> {
        TODO("Implement me")
    }
}