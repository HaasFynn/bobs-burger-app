package ch.fhaas.real_estate_rest_api

import ch.fhaas.real_estate_rest_api.entity.*
import ch.fhaas.real_estate_rest_api.services.JsonReader
import ch.fhaas.real_estate_rest_api.services.RequestBuilder
import ch.fhaas.real_estate_rest_api.services.RequestClient
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("/")
@ComponentScan
class RequestHandler(
    private val requestBuilder: RequestBuilder,
    private val jsonReader: JsonReader,
    private val requestClient: RequestClient
) {


    //TODO: LATER APPROACH: Implement Requester.kt to Request Data from other API's and convert Data to own format. Add @Service Annotation
    // ETL Pipeline with *Spring Cloud Task* -> https://www.baeldung.com/spring-cloud-data-flow-etl

    companion object {
        private const val UNCOMPLETE_LIST_HEADER_RESPONSE: String = "was not able to get all wanted entries"
    }

    @RequestMapping("/")
    @ResponseBody
    private fun main(): Any {
        val url = "http://localhost8080/"
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
        return "<h1>Test Succeeded</h1>"
    }

    @GetMapping(
        "/characters",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getCharacter(
        @RequestParam(name = "amount", required = false)
        amount: Int?,
        @RequestParam(name = "name", required = false)
        name: String?,
        @RequestParam(name = "gender", required = false)
        gender: String?,
        @RequestParam(name = "age", required = false)
        age: Int?,
        @RequestParam(name = "hair", required = false)
        hair: String?,
        @RequestParam(name = "occupation", required = false)
        occupation: String?,
        @RequestParam(name = "episode", required = false)
        episode: Int?,
        @RequestParam(name = "voiceActor", required = false)
        voiceActor: String?
    ): ResponseEntity<Character> {
        val map: HashMap<String, Any?> =
            parametersToHashMap(Character.searchKeys, amount, name, gender, age, hair, occupation, episode, voiceActor)
        val url: String = requestBuilder.buildUrl("character", map)
        val character = jsonReader.getCharacter(
            requestClient.request(url).`object`
        )
        return character?.let { ResponseEntity(character, HttpStatus.OK) } ?: ResponseEntity(HttpStatus.BAD_REQUEST)
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
        @RequestParam(name = "seasonNum", required = false)
        seasonNum: Int,
        @RequestParam(name = "episode", required = false)
        episodeNum: Int,
        @RequestParam(name = "totalViewer", required = false)
        occupation: String
    ): ResponseEntity<Episode> {
        TODO("Implement me")
    }

    @GetMapping(
        "/burgers",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getBurgers(
        @RequestParam(name = "amount", required = false)
        amount: Int,
        @RequestParam(name = "name", required = false)
        name: String,
        @RequestParam(name = "price", required = false)
        price: Double,
        @RequestParam(name = "seasonNum", required = false)
        seasonNum: Int,
        @RequestParam(name = "episode", required = false)
        episodeNum: Int
    ): ResponseEntity<Burger> {
        TODO("Implement me")
    }


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
    ): ResponseEntity<StoreNextDoor> {
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
    ): ResponseEntity<PestControlTruck> {
        TODO("Implement me")
    }

    fun parametersToHashMap(keys: Array<String>, vararg params: Any?): HashMap<String, Any?> =
        keys.zip(params).toMap() as HashMap<String, Any?>


}