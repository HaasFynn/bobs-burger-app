package ch.fhaas.bobs_burger_api

import ch.fhaas.bobs_burger_api.entity.*
import ch.fhaas.bobs_burger_api.services.JsonReader
import ch.fhaas.bobs_burger_api.services.RequestBuilder
import ch.fhaas.bobs_burger_api.services.RequestClient
import ch.fhaas.bobs_burger_api.services.entity_services.CharacterService
import kong.unirest.core.json.JSONArray
import kong.unirest.core.json.JSONObject
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("/")
@ComponentScan
class RequestHandler(
    private val requestBuilder: RequestBuilder,
    private val jsonReader: JsonReader,
    private val requestClient: RequestClient,
    private val characterService: CharacterService
) {
    @RequestMapping("/")
    @ResponseBody
    private fun main(): Any {
        val url = "http://localhost:8080/"
        return """
            |<h1>Bob's Burger App</h1>
            |Possible Requests:
            |<ul>
            |<li><a href="${url}characters?limit=50">Characters</a></li>
            |<li><a href="${url}episodes?limit=50">Episodes</a></li>
            |<li><a href="${url}burgers?limit=50">Burgers</a></li>
            |<li><a href="${url}storeNextDoor?limit=50">StoreNextDoor</a></li>
            |<li><a href="${url}pestControlTruck?limit=50">PestControlTruck</a></li>
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
        @RequestParam(name = "limit", required = false)
        limit: Int = 0,
        @RequestParam(name = "name", required = false)
        name: String = "",
        @RequestParam(name = "gender", required = false)
        gender: String = "",
        @RequestParam(name = "age", required = false)
        age: Int = 0,
        @RequestParam(name = "hair", required = false)
        hair: String = "",
        @RequestParam(name = "occupation", required = false)
        occupation: String = "",
        @RequestParam(name = "episode", required = false)
        episode: Int = 0,
        @RequestParam(name = "voiceActor", required = false)
        voiceActor: String = ""
    ): ResponseEntity<List<Character>> {
        val map = parametersToHashMap(
            Character.searchKeys,
            arrayOf(limit, name, gender, age, hair, occupation, episode, voiceActor)
        )
        val url: String = requestBuilder.buildUrl("characters", map)
        return ResponseEntity.ok(getEntities(url, jsonReader::getCharacter))
    }

    @GetMapping(
        "/episodes",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getEpisode(
        @RequestParam(name = "limit", required = false)
        limit: Int = 0,
        @RequestParam(name = "name", required = false)
        name: String = "",
        @RequestParam(name = "productionCode", required = false)
        productionCode: String = "",
        @RequestParam(name = "airDate", required = false)
        airDate: String = "",
        @RequestParam(name = "seasonNum", required = false)
        seasonNum: Int = 0,
        @RequestParam(name = "episode", required = false)
        episodeNum: Int = 0,
        @RequestParam(name = "totalViewer", required = false)
        occupation: String = ""
    ): ResponseEntity<List<Episode>> {
        val map = parametersToHashMap(
            Character.searchKeys,
            arrayOf(limit, name, productionCode, airDate, seasonNum, episodeNum, occupation)
        )
        val url: String = buildUrl("episodes", map)
        return ResponseEntity.ok(getEntities(url, jsonReader::getEpisode))
    }

    @GetMapping(
        "/burgers",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getBurgers(
        @RequestParam(name = "limit", required = false)
        limit: Int = 0,
        @RequestParam(name = "name", required = false)
        name: String = "",
        @RequestParam(name = "price", required = false)
        price: Double = 0.0,
        @RequestParam(name = "seasonNum", required = false)
        seasonNum: Int = 0,
        @RequestParam(name = "episode", required = false)
        episodeNum: Int = 0
    ): ResponseEntity<List<Burger>> {
        val map = parametersToHashMap(
            Character.searchKeys,
            arrayOf(limit, name, price, seasonNum, episodeNum)
        )
        val url: String = buildUrl("burgerOfTheDay", map)
        return ResponseEntity.ok(getEntities(url, jsonReader::getBurger))
    }

    @GetMapping(
        "/storeNextDoor",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getStoreNextDoor(
        @RequestParam(name = "limit", required = false)
        limit: Int = 0,
        @RequestParam(name = "name", required = false)
        name: String = "",
        @RequestParam(name = "seasonNum", required = false)
        seasonNum: Int = 0,
        @RequestParam(name = "episode", required = false)
        episodeNum: Int = 0
    ): ResponseEntity<List<StoreNextDoor>> {
        val map = parametersToHashMap(
            Character.searchKeys,
            arrayOf(limit, name, seasonNum, episodeNum)
        )
        val url: String = buildUrl("storeNextDoor", map)
        return ResponseEntity.ok(getEntities(url, jsonReader::getStoreNextDoor))
    }

    @GetMapping(
        "/pestControlTruck",
        produces = ["application/json"]
    )
    @ResponseBody
    private fun getPestControlTruck(
        @RequestParam(name = "limit", required = false)
        limit: Int = 0,
        @RequestParam(name = "name", required = false)
        name: String = "",
        @RequestParam(name = "seasonNum", required = false)
        seasonNum: Int = 0,
        @RequestParam(name = "episode", required = false)
        episodeNum: Int = 0
    ): ResponseEntity<List<PestControlTruck>> {
        val map = parametersToHashMap(
            Character.searchKeys,
            arrayOf(limit, name, seasonNum, episodeNum)
        )
        val url: String = buildUrl("storeNextDoor", map)
        return ResponseEntity.ok(getEntities(url, jsonReader::getPestControlTruck))
    }

    fun parametersToHashMap(keys: Array<String>, params: Array<Any>): Map<String, Any> {
        return keys.zip(filterParameters(params)).toMap()
    }

    private fun filterParameters(params: Array<Any>): List<Any> {
        return params.filter {
            when (it) {
                is String -> it.isNotBlank()
                0 -> false
                0.0 -> false
                else -> true
            }
        }
    }

    private fun buildUrl(directory: String, map: Map<String, Any>) = requestBuilder.buildUrl(directory, map)

    private fun <T : Entity> getEntities(url: String, jsonFunction: (json: JSONObject) -> (T?)): List<T> {
        val json: JSONArray = requestClient.request(url).array
        return (0 until json.length()).fold(emptyList()) { list, index ->
            val character: T? = jsonFunction(json.getJSONObject(index))
            character?.let { list + character } ?: list
        }
    }
}
