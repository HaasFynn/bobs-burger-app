package ch.fhaas.real_estate_rest_api

import ch.fhaas.real_estate_rest_api.dao.RealEstateDao
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@ComponentScan
class RequestHandler(private val realEstateDao: RealEstateDao) {

    //TODO: Implement Requester.kt to Request Data from other API's and convert Data to own format. Add @Service Annotation
    // ETL Pipeline with *Spring Cloud Task* -> https://www.baeldung.com/spring-cloud-data-flow-etl

    @RequestMapping("/")
    private fun main(): Any {
        return """
            |<h1>Home Page</h1>
            |<p>To be continued...</p>
            |
        """.trimMargin()
    }

    @GetMapping("/test")
    @ResponseBody
    private fun helloWorld(): Any {
        return "<h1>Test Succeeded</h1>"
    }

    @GetMapping("/generated-data")
    @ResponseBody
    private fun getGeneratedValues(@RequestParam("amount") amount: Int): Any {
        if (amount == 0) return HttpStatus.BAD_REQUEST
        return getListOfRealEstates(amount)
    }

    private fun getListOfRealEstates(amount: Int) = realEstateDao.findAll().take(amount)

    @GetMapping("/real-data")
    @ResponseBody
    private fun get(@RequestParam("amount") amount: Int): Any {
        if (amount == 0) return HttpStatus.BAD_REQUEST
        TODO("implement Requester")
    }

}
