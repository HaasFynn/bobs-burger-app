package ch.fhaas.real_estate_rest_api

import ch.fhaas.real_estate_rest_api.dao.RealEstateDao
import ch.fhaas.real_estate_rest_api.entity.RealEstate
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.web.bind.annotation.*


@RequestMapping("/")
@RestController
class RequestHandler(private val realEstateDao: RealEstateDao) {

    //TODO: Implement Requester.kt to Request Data from other API's and convert Data to own format. Add @Service Annotation
    // ETL Pipeline with *Spring Cloud Task* -> https://www.baeldung.com/spring-cloud-data-flow-etl

    @GetMapping("/generated-data")
    @ResponseBody
    fun getGeneratedValues(@RequestParam("amount") amount: Int): Any {
        if (amount == 0) return HttpStatus.BAD_REQUEST
        val list = realEstateDao.findAll().take(amount)
        return list
    }

    @GetMapping("/real-data")
    @ResponseBody
    fun get(@RequestParam("amount") amount: Int): Any {
        if (amount == 0) return HttpStatus.BAD_REQUEST

        TODO("implement Requester")
    }

    private fun createValidResponse(list: List<RealEstate>?): ResponseEntity<Any> {
        val header = HttpHeaders()
        return ResponseEntity<Any>(list, header, HttpStatus.OK)
    }


}
