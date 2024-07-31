package ch.fhaas.real_estate_rest_api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RealEstateRestApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(RequestHandler::class.java, *args)
}


