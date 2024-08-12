package ch.fhaas.real_estate_rest_api

import ch.fhaas.real_estate_rest_api.services.entity_services.CharacterService
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/")
@ComponentScan
class RequestHandler(private val characterService: CharacterService) {


    //TODO: LATER APPROACH: Implement Requester.kt to Request Data from other API's and convert Data to own format. Add @Service Annotation
    // ETL Pipeline with *Spring Cloud Task* -> https://www.baeldung.com/spring-cloud-data-flow-etl

    companion object {
        private const val UNCOMPLETE_LIST_HEADER_RESPONSE: String = "was not able to get all wanted entries"
    }

    @RequestMapping("/")
    private fun main(): Any {
        return """
            |<h1>Home Page</h1>
            |<p>To be continued...</p>
            |
        """.trimMargin()
    }

    @GetMapping("/test")
    private fun helloWorld(): Any {
        return "<h1>Test Succeeded</h1>"
    }

}
