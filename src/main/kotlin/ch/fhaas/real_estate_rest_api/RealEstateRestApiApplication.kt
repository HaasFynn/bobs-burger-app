package ch.fhaas.real_estate_rest_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RealEstateRestApiApplication

fun main(args: Array<String>) {
    runApplication<RealEstateRestApiApplication>(*args)
}
