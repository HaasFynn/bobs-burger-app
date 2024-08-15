package ch.fhaas.real_estate_rest_api

import ch.fhaas.real_estate_rest_api.entity.Character
import ch.fhaas.real_estate_rest_api.entity.Episode
import ch.fhaas.real_estate_rest_api.services.entity_services.CharacterService
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.*

@RestController("/")
@ComponentScan
class RequestHandler(private val characterService: CharacterService) {


    //TODO: LATER APPROACH: Implement Requester.kt to Request Data from other API's and convert Data to own format. Add @Service Annotation
    // ETL Pipeline with *Spring Cloud Task* -> https://www.baeldung.com/spring-cloud-data-flow-etl

    companion object {
        private const val UNCOMPLETE_LIST_HEADER_RESPONSE: String = "was not able to get all wanted entries"
    }

    @RequestMapping("/")
    @ResponseBody
    private fun main(): Any {
        return """
            |<h1>Bob's Burger App</h1>
            |<p>To be continued...</p>
            |
        """.trimMargin()
    }

    @GetMapping("/test")
    @ResponseBody
    private fun helloWorld(): Any {
        return "<h1>Test Succeeded</h1>"
    }

    @GetMapping(
        "/characters",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getCharacter(
        @RequestParam(name = "amount", required = false)
        amount: Int,
        @RequestParam(name = "name", required = false)
        name: String,
        @RequestParam(name = "gender", required = false)
        gender: String,
        @RequestParam(name = "age", required = false)
        age: Int,
        @RequestParam(name = "hair", required = false)
        hair: String,
        @RequestParam(name = "currentOccupation", required = false)
        currentOccupation: String,
        @RequestParam(name = "occupation", required = false)
        occupation: String,
        @RequestParam(name = "episode", required = false)
        episode: Int,
        @RequestParam(name = "voiceActor", required = false)
        voiceActor: String
    ): Character {
        TODO("Implement me")
    }

    @GetMapping(
        "/episodes",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getEpisode(
        @RequestParam(name = "amount", required = false)
        amount: Int,
        @RequestParam(name = "name", required = false)
        name: String,
        @RequestParam(name = "productionCode", required = false)
        productionCode: String,
        @RequestParam(name = "airDate", required = false)
        airDate: String,
        @RequestParam(name = "season", required = false)
        season: Int,
        @RequestParam(name = "episodeNum", required = false)
        episode: Int,
        @RequestParam(name = "totalViewer", required = false)
        occupation: String
    ): Episode {
        TODO("Implement me")
    }
}
