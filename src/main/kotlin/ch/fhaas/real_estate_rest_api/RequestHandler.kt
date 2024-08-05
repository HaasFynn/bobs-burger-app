package ch.fhaas.real_estate_rest_api

import ch.fhaas.real_estate_rest_api.dao.RealEstateDao
import ch.fhaas.real_estate_rest_api.entity.RealEstate
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController("/")
@ComponentScan
class RequestHandler(private val realEstateDao: RealEstateDao) {


    //TODO: Implement Requester.kt to Request Data from other API's and convert Data to own format. Add @Service Annotation
    // ETL Pipeline with *Spring Cloud Task* -> https://www.baeldung.com/spring-cloud-data-flow-etl

    companion object {
        private const val UNCOMPLETE_LIST_HEADER_RESPONSE: String = "Was not able to get all wanted Real Estates"
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

    @GetMapping("/generated-data")
    private fun getGeneratedValues(@RequestParam("amount") amount: Int): Any {
        if (amount == 0) return HttpStatus.BAD_REQUEST
        val list: List<RealEstate> = getListOfRealEstates(amount)

        if (list.isEmpty()) return HttpStatus.INTERNAL_SERVER_ERROR
        if (list.size < amount) return getUncompleteListResponse(list, amount)
        return list
    }

    private fun getUncompleteListResponse(list: List<RealEstate>, amount: Int): Any {
        val header = HttpHeaders()
        header.set("Warning", UNCOMPLETE_LIST_HEADER_RESPONSE + "Amount: ${list.size}, Requested: $amount")
        return ResponseEntity(list, header, HttpStatus.CONFLICT).headers
    }

    private fun getListOfRealEstates(amount: Int) = realEstateDao.findAll().take(amount)

    @GetMapping("/real-data")
    private fun get(@RequestParam("amount") amount: Int): Any {
        if (amount == 0) return HttpStatus.BAD_REQUEST
        TODO("implement Requester")
    }

}
